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
import org.springframework.transaction.annotation.Transactional;

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
                .map(cartMapper::toCartResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CartResponse getCartById(Long cartId) {
        Cart cart = this.getCart(cartId);
        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("User ID: " + userId + " not found!"));
        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse createCart(CartRequest cartRequest) {
        Cart cart = cartRepository.findByUserId(cartRequest.getUserId())
                .orElseGet(() -> {
                    Cart newCart = cartMapper.toEntity(cartRequest);
                    newCart.setItems(new ArrayList<>());
                    newCart.setTotalAmount(0.0);
                    return newCart;
                });
        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse updateCart(Long cartId, CartUpdateRequest cartUpdateRequest) {
        Cart cart = this.getCart(cartId);

        if(cartUpdateRequest.getTotalAmount() != null) {
            cart.setTotalAmount(cartUpdateRequest.getTotalAmount());
        }

        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }

    @Override
    public String clearCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        cart.getItems().clear();
        cart.setTotalAmount(0.0);
        cartRepository.save(cart);
        return "Cart cleared successfully";
    }


    @Override
    @Transactional
    public String deleteCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new CartNotFoundException("Cart not found with ID: " + cartId);
        }
        cartRepository.deleteById(cartId);
        return "Cart with ID " + cartId + " was successfully deleted";
    }

    @Override
    @Transactional
    public String deleteCartByUserId(Long userId) {
        cartRepository.deleteByUserId(userId);
        return "Cart with user ID " + userId + " was successfully deleted";
    }

    private Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));
    }

}
