package com.order_service.client;

import com.order_service.client.dto.CartApiResponse;
import com.order_service.exception.CartServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CartApiFallback implements CartApi{

    @Override
    public CartApiResponse getCartByUserId(Long userId) {
        log.error("Fallback activated: getCartByUserId failed for userId {}", userId);
        throw new CartServiceUnavailableException("Cart service is unavailable. Cannot retrieve cart for user ID: " + userId);
    }

    @Override
    public void clearCart(Long userId) {
        log.error("Fallback activated: clearCart failed for userId {}", userId);
        throw new CartServiceUnavailableException("Cart service is unavailable. Cannot clear cart for user ID: " + userId);
    }
}
