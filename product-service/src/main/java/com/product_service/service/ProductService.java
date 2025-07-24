package com.product_service.service;

import com.product_service.dto.ProductRequest;
import com.product_service.dto.ProductStockRequest;
import com.product_service.dto.ProductUpdateRequest;
import com.product_service.dto.ProductResponse;
import com.product_service.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(String productId);
    ProductResponse createProduct(ProductRequest ProductRequest);
    void checkAndDecreaseStock(List<ProductStockRequest> productList);
    void increaseStock(List<ProductStockRequest> productList);
    String updateProduct(String productId, ProductUpdateRequest productUpdateRequest);
    String deleteProduct(String productId);
}
