package com.product_service.service;

import com.product_service.dto.ProductInDTO;
import com.product_service.dto.ProductOutDTO;
import com.product_service.kafka.event.StockEvent;
import com.product_service.kafka.producer.KafkaProducer;
import com.product_service.mapper.ProductInDTOToProduct;
import com.product_service.mapper.ProductToProductOutDTO;
import com.product_service.model.Product;
import com.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final ProductInDTOToProduct productInDTOToProduct;
    private final ProductToProductOutDTO productToProductOutDTO;
    private final KafkaProducer kafkaProducer;


    @Override
    public ProductOutDTO createProduct(ProductInDTO productInDTO) {

        Product product = productInDTOToProduct.map(productInDTO);
        Product savedProduct = productRepository.save(product);

        this.updateStock(savedProduct.getId(), productInDTO.getStock());

        return productToProductOutDTO.map(product);
    }

    @Override
    public ProductOutDTO updateProduct(String productId, ProductInDTO productInDTO) {

        Product product = this.getProduct(productId);

        Optional.ofNullable(productInDTO.getName()).ifPresent(product::setName);
        Optional.ofNullable(productInDTO.getDescription()).ifPresent(product::setDescription);
        Optional.ofNullable(productInDTO.getCategory()).ifPresent(product::setCategory);
        Optional.ofNullable(productInDTO.getUnitPrice()).ifPresent(product::setUnitPrice);

        this.updateStock(productId, productInDTO.getStock());

        productRepository.save(product);
        return productToProductOutDTO.map(product);
    }

    @Override
    public ProductOutDTO getProductById(String productId) {

        Product product = this.getProduct(productId);

        return productToProductOutDTO.map(product);
    }

    @Override
    public List<ProductOutDTO> getAllProducts() {

        return productRepository.findAll().stream()
                .map(productToProductOutDTO::map)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteProduct(String productId) {

        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
        return "Product with ID " + productId + " was successfully deleted";

    }

    @Override
    public Product getProduct(String productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    @Override
    public void updateStock(String productId, Integer stock) {

        StockEvent stockEvent = new StockEvent();
        stockEvent.setProductId(productId);
        stockEvent.setStock(stock);

        kafkaProducer.sendStockEvent(stockEvent);
    }
}
