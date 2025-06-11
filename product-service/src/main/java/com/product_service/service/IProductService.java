package com.product_service.service;

import com.product_service.dto.ProductInDTO;
import com.product_service.dto.ProductOutDTO;
import com.product_service.kafka.event.StockEvent;
import com.product_service.model.Product;

import java.util.List;

public interface IProductService {

    ProductOutDTO createProduct(ProductInDTO productInDTO);

    ProductOutDTO updateProduct(String productId, ProductInDTO productInDTO);

    ProductOutDTO getProductById(String productId);

    List<ProductOutDTO> getAllProducts();

    String deleteProduct(String productId);

    Product getProduct(String productId);

    void updateStock(String productId, Integer stock);
}
