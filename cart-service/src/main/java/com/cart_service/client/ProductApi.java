package com.cart_service.client;

import com.cart_service.client.dto.ProductClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductApi {

    @GetMapping("/api/product/{productId}")
    public ProductClientResponse getProductById(@PathVariable("productId") Long productId);
}
