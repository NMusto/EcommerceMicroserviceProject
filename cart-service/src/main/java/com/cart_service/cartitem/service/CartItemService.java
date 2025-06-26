package com.cart_service.cartitem.service;

import com.cart_service.cartitem.dto.CartItemRequest;
import com.cart_service.cart.entity.Cart;
import com.cart_service.cartitem.entity.CartItem;

import java.util.List;

public interface CartItemService {

    CartItem addItemToCart(Long cartId, CartItemRequest cartItemRequest);
    CartItem updateItem(Long itemId, Integer newQuantity);
    void deleteItemById(Long itemId);
    CartItem getItemById(Long itemId);
    List<CartItem> getItemsByCartId(Long cartId);
    double recalculateCartTotalAmount(Cart cart);
    CartItem getCartItem(Long itemId);

}
