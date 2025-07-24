package com.user_service.client.cart;

import com.user_service.client.cart.dto.CartApiRequest;
import com.user_service.client.cart.dto.CartApiResponse;
import com.user_service.exception.CartServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CartApiFallback implements CartApi {

    @Override
    public CartApiResponse getCartByUserId(Long userId) {
        log.error("Fallback activated: getCartByUserId failed for userId {}", userId);
        throw new CartServiceUnavailableException("Cart service is unavailable. Cannot retrieve Cart for user ID: " + userId);
    }

    @Override
    public void createCart(CartApiRequest cartApiRequest) {
        log.error("Fallback activated: createCart failed for userId {}", cartApiRequest.userId());
        throw new CartServiceUnavailableException("Cart service is unavailable. Cannot create cart for user ID: " + cartApiRequest.userId());
    }

    @Override
    public String deleteCartByUserId(Long userId) {
        log.error("Fallback activated: deleteCartByUserId failed for userId {}", userId);
        throw new CartServiceUnavailableException("Cart service is unavailable. Cannot delete cart for user ID: " + userId);
    }


}
