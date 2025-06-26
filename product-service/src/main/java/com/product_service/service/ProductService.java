package com.product_service.service;

import com.product_service.dto.ProductRequest;
import com.product_service.dto.ProductUpdateRequest;
import com.product_service.dto.ProductResponse;
import com.product_service.entity.Product;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest ProductRequest);

    String updateProduct(String productId, ProductUpdateRequest productUpdateRequest);

    ProductResponse getProductById(String productId);

    List<Product> getAllProducts();

    String deleteProduct(String productId);

    Product getProduct(String productId);

    void updateStock(String productId, Integer stock);
}
