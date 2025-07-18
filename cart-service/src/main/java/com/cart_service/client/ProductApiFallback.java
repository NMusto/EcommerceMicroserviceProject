package com.cart_service.client;

import com.cart_service.client.dto.ProductApiResponse;
import com.cart_service.exception.ProductServiceUnavailableException;
import org.springframework.stereotype.Component;

@Component
public class ProductApiFallback implements ProductApi{

    @Override
    public ProductApiResponse getProductById(String productId) {
        throw new ProductServiceUnavailableException("Product service is unavailable. Failed to get product with ID: " + productId);
    }
}
