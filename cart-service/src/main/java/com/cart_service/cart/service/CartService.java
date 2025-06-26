package com.cart_service.cart.service;

import com.cart_service.cart.dto.CartResponse;
import com.cart_service.cart.dto.CartRequest;
import com.cart_service.cart.dto.CartUpdateRequest;
import com.cart_service.cart.entity.Cart;

import java.util.List;

public interface CartService {

    List<CartResponse> getAllCarts();
    CartResponse getCartById(Long cartId);
    CartResponse getCartByUserId(Long userId);
    CartResponse createCart(CartRequest cartRequest);
    CartResponse updateCart(Long cartId, CartUpdateRequest cartUpdateRequest);
    void deleteCart(Long cartId);
    Cart getCart(Long cartId);

}
