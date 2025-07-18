package com.user_service.client;

import com.user_service.client.dto.CartRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cart-service")
public interface CartApi {

    @PostMapping("/api/cart")
    void createCart(@RequestBody CartRequest cartRequest);
}
