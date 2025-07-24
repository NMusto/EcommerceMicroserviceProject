package com.order_service.client.cart;

import com.order_service.client.cart.dto.CartApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service", fallback = CartApiFallback.class)
public interface CartApi {

    @GetMapping("/api/cart/user/{userId}")
    CartApiResponse getCartByUserId(@PathVariable("userId") Long userId);

    @DeleteMapping("api/cart/clearitems/{userId}")
    void clearCart(@PathVariable("userId") Long userId);
}
