package com.product_service.service;

import com.product_service.client.dto.InventoryApiResponse;
import com.product_service.dto.ProductRequest;
import com.product_service.dto.ProductUpdateRequest;
import com.product_service.dto.ProductResponse;
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

import java.util.List;
import java.util.Optional;
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

        InventoryApiResponse inventoryApiResponse = inventoryApi.getInventoryByProductId(productId);    // Fallback desde IInventoryApi, (devuelve un inventoryDTO)

        productResponse.setStock(inventoryApiResponse.getStock());

        return productResponse;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = productRequestToProduct.map(productRequest);
        Product savedProduct = productRepository.save(product);

        this.updateStock(savedProduct.getId(), productRequest.getStock());

        ProductResponse productResponse = productToProductResponse.map(product);
        productResponse.setStock(productRequest.getStock());

        return productResponse;
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
