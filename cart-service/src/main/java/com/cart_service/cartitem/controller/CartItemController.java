package com.cart_service.cartitem.controller;

import com.cart_service.cart.dto.CartRequest;
import com.cart_service.cart.dto.CartResponse;
import com.cart_service.cart.dto.CartUpdateRequest;
import com.cart_service.cartitem.dto.CartItemRequest;
import com.cart_service.cartitem.dto.CartItemResponse;
import com.cart_service.cartitem.dto.CartItemUpdateRequest;
import com.cart_service.cartitem.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartitem")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItemResponse>> getItemsByCartId(@PathVariable Long cartId) {
        List<CartItemResponse> cartItems = cartItemService.getItemsByCartId(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cartItems);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<CartItemResponse> getCartItemById(@PathVariable Long itemId) {
        CartItemResponse cartItemResponse = cartItemService.getItemById(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemResponse);
    }

    @PostMapping("/cartId")
    public ResponseEntity<CartItemResponse> addItemToCart(@PathVariable Long cartId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        CartItemResponse cartItemResponse = cartItemService.addItemToCart(cartId, cartItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemResponse);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<CartItemResponse> updateItem(@PathVariable Long itemId,
                                                   @RequestBody CartItemUpdateRequest cartItemUpdateRequest) {
        CartItemResponse cartItemResponse = cartItemService.updateItem(itemId, cartItemUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(cartItemResponse);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
        String response = cartItemService.deleteItemById(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
