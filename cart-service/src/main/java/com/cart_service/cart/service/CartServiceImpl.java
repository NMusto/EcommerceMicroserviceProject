package com.cart_service.cart.service;

import com.cart_service.cart.dto.CartResponse;
import com.cart_service.cart.dto.CartRequest;
import com.cart_service.cart.dto.CartUpdateRequest;
import com.cart_service.cart.mapper.CartMapper;
import com.cart_service.cart.entity.Cart;
import com.cart_service.cart.repository.CartRepository;
import com.cart_service.exception.CartNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;


    @Override
    public List<CartResponse> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(cartMapper::toCartOutDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartResponse getCartById(Long cartId) {
        Cart cart = this.getCart(cartId);
        return cartMapper.toCartOutDto(cart);
    }

    @Override
    public CartResponse getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("User ID: " + userId + " not found!"));
        return cartMapper.toCartOutDto(cart);
    }

    @Override
    public CartResponse createCart(CartRequest cartRequest) {
        Cart cart = cartRepository.findByUserId(cartRequest.getUserId())
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .userId(cartRequest.getUserId())
                                .items(new ArrayList<>())
                                .totalAmount(0.0)
                                .build()
                ));
        return cartMapper.toCartOutDto(cart);
    }

    @Override
    public CartResponse updateCart(Long cartId, CartUpdateRequest cartUpdateRequest) {
        Cart cart = this.getCart(cartId);
        cart.setTotalAmount(cartUpdateRequest.getTotalAmount());
        cartRepository.save(cart);

        return cartMapper.toCartOutDto(cart);
    }

    @Override
    public void deleteCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new CartNotFoundException("Cart not found with ID: " + cartId);
        }
        cartRepository.deleteById(cartId);
    }

    @Override
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));
    }
}
