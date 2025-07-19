package com.user_service.client.cart;

import com.user_service.client.cart.dto.CartApiRequest;
import com.user_service.exception.CartServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CartApiFallback implements CartApi {

    @Override
    public void createCart(CartApiRequest cartApiRequest) {
        throw new CartServiceUnavailableException("Cart service is unavailable. Cannot create cart for user ID: " + cartApiRequest.userId());
    }
}
