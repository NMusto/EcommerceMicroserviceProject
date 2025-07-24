package com.user_service.client.cart;

import com.user_service.client.cart.dto.CartApiRequest;
import com.user_service.client.cart.dto.CartApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cart-service", fallback = CartApiFallback.class)
public interface CartApi {

    @GetMapping("/api/cart/user/{userId}")
    CartApiResponse getCartByUserId(@PathVariable("userId") Long userId);
    @PostMapping("/api/cart")
    void createCart(@RequestBody CartApiRequest cartApiRequest);
    @DeleteMapping("/api/cart/user/{userId}")
    String deleteCartByUserId(@PathVariable Long userId);
}
