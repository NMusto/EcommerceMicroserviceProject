package com.product_service.service;

import com.product_service.dto.ProductCreateDTO;
import com.product_service.dto.ProductUpdateDTO;
import com.product_service.dto.ProductOutDTO;
import com.product_service.exception.ProductNotFoundException;
import com.product_service.kafka.event.StockEvent;
import com.product_service.kafka.producer.KafkaProducer;
import com.product_service.mapper.ProductCreateDTOToProduct;
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
    private final ProductCreateDTOToProduct productCreateDTOToProduct;
    private final ProductToProductOutDTO productToProductOutDTO;
    private final KafkaProducer kafkaProducer;


    @Override
    public ProductOutDTO createProduct(ProductCreateDTO productCreateDTO) {

        Product product = productCreateDTOToProduct.map(productCreateDTO);
        Product savedProduct = productRepository.save(product);

        this.updateStock(savedProduct.getId(), productCreateDTO.getStock());

        ProductOutDTO productOutDTO = productToProductOutDTO.map(product);
        productOutDTO.setStock(productCreateDTO.getStock());

        return productOutDTO;
    }

    @Override
    public String updateProduct(String productId, ProductUpdateDTO productUpdateDTO) {

        Product product = this.getProduct(productId);

        Optional.ofNullable(productUpdateDTO.getName()).ifPresent(product::setName);
        Optional.ofNullable(productUpdateDTO.getDescription()).ifPresent(product::setDescription);
        Optional.ofNullable(productUpdateDTO.getCategory()).ifPresent(product::setCategory);
        Optional.ofNullable(productUpdateDTO.getUnitPrice()).ifPresent(product::setUnitPrice);

        if (productUpdateDTO.getStock() != null) {
            this.updateStock(productId, productUpdateDTO.getStock());
        }

        productRepository.save(product);
        return "Product with ID " + productId + " was successfully updated!";
    }

    @Override
    public ProductOutDTO getProductById(String productId) {

        Product product = this.getProduct(productId);

        ProductOutDTO productOutDTO = productToProductOutDTO.map(product);



        return ;
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public String deleteProduct(String productId) {

        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
        return "Product with ID " + productId + " was successfully deleted";

    }

    @Override
    public Product getProduct(String productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public void updateStock(String productId, Integer stock) {

        StockEvent stockEvent = new StockEvent();
        stockEvent.setProductId(productId);
        stockEvent.setStock(stock);

        kafkaProducer.sendStockEvent(stockEvent);
    }
}
