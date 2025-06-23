package com.cart_service.service;

import com.cart_service.dto.CartOutDto;
import com.cart_service.dto.CartiInDto;
import com.cart_service.dto.UpdateCartDto;
import com.cart_service.repository.ICartItemRepository;
import com.cart_service.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final ICartRepository cartRepository;


    @Override
    public List<CartOutDto> getAllCarts() {
        return null;
    }

    @Override
    public CartOutDto getCartById(Long cartId) {
        return null;
    }

    @Override
    public CartOutDto getCartByUserId(Long userId) {
        return null;
    }

    @Override
    public CartOutDto createCart(CartiInDto cartiInDto) {
        return null;
    }

    @Override
    public CartOutDto updateCart(Long cartId, UpdateCartDto updateCartDto) {
        return null;
    }

    @Override
    public void deleteCart(Long cartId) {

    }
}
