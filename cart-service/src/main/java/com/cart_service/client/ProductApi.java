package com.cart_service.client;

import com.cart_service.client.dto.ProductApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductApi {

    @GetMapping("/api/product/{productId}")
    public ProductApiResponse getProductById(@PathVariable("productId") String productId);
}
