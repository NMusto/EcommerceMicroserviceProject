package com.cart_service.cart.controller;

import com.cart_service.cart.dto.CartRequest;
import com.cart_service.cart.dto.CartResponse;
import com.cart_service.cart.dto.CartUpdateRequest;
import com.cart_service.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;


    @GetMapping
    public ResponseEntity<List<CartResponse>> getAllCarts() {
        List<CartResponse> carts = cartService.getAllCarts();
        return ResponseEntity.status(HttpStatus.OK).body(carts);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable Long cartId) {
        CartResponse cartResponse = cartService.getCartById(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartResponse> getCartByUserId(@PathVariable Long userId) {
        CartResponse cartResponse = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
    }

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@Valid @RequestBody CartRequest cartRequest) {
        CartResponse cartResponse = cartService.createCart(cartRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponse);
    }

    @PatchMapping("/{cartId}")
    public ResponseEntity<CartResponse> updateCart(@PathVariable Long cartId,
                                                @RequestBody CartUpdateRequest cartUpdateRequest) {
        CartResponse cartResponse = cartService.updateCart(cartId, cartUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
    }

    @DeleteMapping("/clearitems/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        String response = cartService.clearCart(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        String response = cartService.deleteCart(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteCartByUserId(@PathVariable Long userId) {
        String response = cartService.deleteCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
