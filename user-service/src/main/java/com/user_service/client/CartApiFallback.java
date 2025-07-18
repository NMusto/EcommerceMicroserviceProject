package com.user_service.client;

import com.user_service.client.dto.CartRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CartApiFallback implements CartApi{

    @Override
    public void createCart(CartRequest cartRequest) {
        log.info("CartApiFallback activated");
    }
}
