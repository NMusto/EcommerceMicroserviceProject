package com.cart_service.service;

import com.cart_service.dto.CartItemInDto;
import com.cart_service.dto.CartOutDto;
import com.cart_service.dto.CartiInDto;
import com.cart_service.dto.UpdateCartDto;
import com.cart_service.model.Cart;
import com.cart_service.model.CartItem;

import java.util.List;

public interface ICartService {

    List<CartOutDto> getAllCarts();
    CartOutDto getCartById(Long cartId);
    CartOutDto getCartByUserId(Long userId);
    CartOutDto createCart(CartiInDto cartiInDto);
    CartOutDto updateCart(Long cartId, UpdateCartDto updateCartDto);
    void deleteCart(Long cartId);

}
