package com.cart_service.cartitem.service;

import com.cart_service.cartitem.dto.CartItemRequest;
import com.cart_service.cart.entity.Cart;
import com.cart_service.cartitem.dto.CartItemResponse;
import com.cart_service.cartitem.dto.CartItemUpdateRequest;
import com.cart_service.cartitem.entity.CartItem;

import java.util.List;

public interface CartItemService {

    List<CartItemResponse> getItemsByCartId(Long cartId);
    CartItemResponse getItemById(Long itemId);
    CartItemResponse addItemToCart(Long cartId, CartItemRequest cartItemRequest);
    CartItemResponse updateItem(Long itemId, CartItemUpdateRequest cartItemUpdateRequest);
    String deleteItemById(Long itemId);
}
