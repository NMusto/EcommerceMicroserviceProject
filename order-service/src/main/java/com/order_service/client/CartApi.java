package com.order_service.client;

import com.order_service.client.dto.CartApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface CartApi {

    @GetMapping("/api/cart/{userId}")
    public CartApiResponse getCartByUserId(@PathVariable("userId") Long userId);
}
