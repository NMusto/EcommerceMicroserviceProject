package com.user_service.client.cart;

import com.user_service.client.cart.dto.CartApiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cart-service", fallback = CartApiFallback.class)
public interface CartApi {

    @PostMapping("/api/cart")
    void createCart(@RequestBody CartApiRequest cartApiRequest);
}
