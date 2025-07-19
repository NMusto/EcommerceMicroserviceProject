package com.user_service.client.cart;

import com.user_service.client.cart.dto.CartApiRequest;
import com.user_service.client.cart.dto.CartApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cart-service", fallback = CartApiFallback.class)
public interface CartApi {

    @PostMapping("/api/cart")
    void createCart(@RequestBody CartApiRequest cartApiRequest);

    @GetMapping("/api/cart/{userId}")
    CartApiResponse getCartByUserId(@PathVariable("userId") Long userId);
}
