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

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final ProductInDTOToProduct productInDTOToProduct;
    private final ProductToProductOutDTO productToProductOutDTO;
    private final KafkaProducer kafkaProducer;


    @Override
    public ProductOutDTO createProduct(ProductInDTO productInDTO) {

        if (productRepository.existsByName(productInDTO.getName())) {
            throw new RuntimeException("Product with name '" + productInDTO.getName() + "' already exists");
        }

        Product product = productInDTOToProduct.map(productInDTO);
        Product savedProduct = productRepository.save(product);

        StockEvent stockEvent = new StockEvent();
        stockEvent.setProductId(savedProduct.getId());
        stockEvent.setStock(productInDTO.getStock());

        kafkaProducer.sendStockEvent(stockEvent);

        return productToProductOutDTO.map(product);
    }

    @Override
    public ProductOutDTO updateProduct(Long productId, ProductInDTO productInDTO) {
        return null;
    }

    @Override
    public ProductOutDTO getProductById(Long productId) {
        return null;
    }

    @Override
    public List<ProductOutDTO> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }
}
