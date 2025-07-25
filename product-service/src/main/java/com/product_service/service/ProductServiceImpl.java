package com.product_service.service;

import com.product_service.client.dto.InventoryApiResponse;
import com.product_service.dto.ProductRequest;
import com.product_service.dto.ProductStockRequest;
import com.product_service.dto.ProductUpdateRequest;
import com.product_service.dto.ProductResponse;
import com.product_service.exception.DuplicateProductNameException;
import com.product_service.exception.InsufficientStockException;
import com.product_service.exception.ProductNotFoundException;
import com.common.models.event.StockEvent;
import com.common.models.event.ProductDeletedEvent;
import com.product_service.kafka.producer.KafkaProducer;
import com.product_service.mapper.ProductRequestToProduct;
import com.product_service.mapper.ProductToProductResponse;
import com.product_service.entity.Product;
import com.product_service.client.InventoryApi;
import com.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductRequestToProduct productRequestToProduct;
    private final ProductToProductResponse productToProductResponse;
    private final KafkaProducer kafkaProducer;
    private final InventoryApi inventoryApi;



    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll().stream()
                .map(product -> productToProductResponse.map(product))
                .collect(Collectors.toList());
    }

    @Override
    //@Cacheable(value = "products", key = "#productId", unless = "#result == null")
    public ProductResponse getProductById(String productId) {

        Product product = this.getProduct(productId);

        ProductResponse productResponse = productToProductResponse.map(product);

        InventoryApiResponse inventoryApiResponse = inventoryApi.getInventoryByProductId(productId);    // Fallback from IInventoryApi, (retrieves inventoryDTO)

        productResponse.setStock(inventoryApiResponse.getStock());

        return productResponse;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        if ( productRepository.existsByNameIgnoreCase(productRequest.getName())) {
            throw new DuplicateProductNameException("Product with name " + productRequest.getName() + " already exists");
        }

        Product product = productRequestToProduct.map(productRequest);
        Product savedProduct = productRepository.save(product);

        this.updateStock(savedProduct.getId(), productRequest.getStock());

        ProductResponse productResponse = productToProductResponse.map(product);
        productResponse.setStock(productRequest.getStock());

        return productResponse;
    }

    @Override
    public void checkAndDecreaseStock(List<ProductStockRequest> productList) {

        Map<String, InventoryApiResponse> inventoryMap = new HashMap<>();

        productList.stream().forEach( product -> {

            InventoryApiResponse inventory = inventoryApi.getInventoryByProductId(product.getProductId());
            inventoryMap.put(product.getProductId(), inventory);

            if (product.getQuantity() > inventory.getStock()) {
                throw new InsufficientStockException(
                        "Insufficient stock for product ID " + product.getProductId() +
                                " (requested: " + product.getQuantity() +
                                ", available: " + inventory.getStock() + ")"
                );
            }
        });

        for (ProductStockRequest product : productList) {

            InventoryApiResponse inventory = inventoryMap.get(product.getProductId());
            Integer updatedStock = inventory.getStock() - product.getQuantity();
            this.updateStock(product.getProductId(), updatedStock);
        }
    }

    @Override
    public void increaseStock(List<ProductStockRequest> productList) {
        for (ProductStockRequest product : productList) {
            InventoryApiResponse inventory = inventoryApi.getInventoryByProductId(product.getProductId());
            Integer updatedStock = inventory.getStock() + product.getQuantity();

            this.updateStock(product.getProductId(), updatedStock);
        }
    }

    @Override
    //@CachePut(value = "products", key = "#productId")
    public String updateProduct(String productId, ProductUpdateRequest productUpdateRequest) {

        Product product = this.getProduct(productId);

        Optional.ofNullable(productUpdateRequest.getName()).ifPresent(product::setName);
        Optional.ofNullable(productUpdateRequest.getDescription()).ifPresent(product::setDescription);
        Optional.ofNullable(productUpdateRequest.getCategory()).ifPresent(product::setCategory);
        Optional.ofNullable(productUpdateRequest.getUnitPrice()).ifPresent(product::setUnitPrice);

        if (productUpdateRequest.getStock() != null) {
            this.updateStock(productId, productUpdateRequest.getStock());
        }

        productRepository.save(product);
        return "Product with ID " + productId + " was successfully updated!";
    }



    @Override
    @Transactional
    //@CacheEvict(value = "products", key = "#productId")
    public String deleteProduct(String productId) {

        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }

        this.deleteProductStock(productId);

        productRepository.deleteById(productId);
        return "Product with ID " + productId + " was successfully deleted";
    }

    private Product getProduct(String productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
    }

    private void updateStock(String productId, Integer stock) {

        StockEvent stockEvent = new StockEvent();
        stockEvent.setProductId(productId);
        stockEvent.setStock(stock);

        kafkaProducer.sendStockEvent(stockEvent);
    }

    private void deleteProductStock(String productId) {

        ProductDeletedEvent productDeletedEvent = new ProductDeletedEvent();
        productDeletedEvent.setProductId(productId);

        kafkaProducer.sendProductDeleteEvent(productDeletedEvent);
    }
}
