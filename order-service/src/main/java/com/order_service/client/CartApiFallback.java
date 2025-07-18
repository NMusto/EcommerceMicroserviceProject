package com.order_service.client;

import com.order_service.client.dto.CartApiResponse;
import org.springframework.stereotype.Component;

@Component
public class CartApiFallback implements CartApi{

    @Override
    public CartApiResponse getCartByUserId(Long userId) {
        return null;
    }

    @Override
    public void clearCart(Long userId) {

    }
}
