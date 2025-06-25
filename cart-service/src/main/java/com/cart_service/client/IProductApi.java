package com.cart_service.client;

import com.cart_service.dto.productDto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface IProductApi {

    @GetMapping("/api/product/{productId}")
    public ProductDto getProductById(@PathVariable("productId") Long productId);
}
