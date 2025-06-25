package com.cart_service.service.cartService;

import com.cart_service.dto.cartDto.CartOutDto;
import com.cart_service.dto.cartDto.CartiInDto;
import com.cart_service.dto.cartDto.UpdateCartDto;
import com.cart_service.model.Cart;

import java.util.List;

public interface ICartService {

    List<CartOutDto> getAllCarts();
    CartOutDto getCartById(Long cartId);
    CartOutDto getCartByUserId(Long userId);
    CartOutDto createCart(CartiInDto cartiInDto);
    CartOutDto updateCart(Long cartId, UpdateCartDto updateCartDto);
    void deleteCart(Long cartId);
    Cart getCart(Long cartId);

}
