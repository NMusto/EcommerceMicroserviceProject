package com.product_service.service;

import com.product_service.dto.ProductRequest;
import com.product_service.dto.ProductUpdateRequest;
import com.product_service.dto.ProductResponse;
import com.product_service.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(String productId);
    ProductResponse createProduct(ProductRequest ProductRequest);
    String updateProduct(String productId, ProductUpdateRequest productUpdateRequest);
    void updateStock(String productId, Integer stock);
    String deleteProduct(String productId);
}
