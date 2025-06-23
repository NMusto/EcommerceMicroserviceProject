package com.cart_service.service;

import com.cart_service.dto.CartItemInDto;
import com.cart_service.model.CartItem;

import java.util.List;

public interface ICartItemService {

    List<CartItem> getItemsByCartId(Long cartId);
    CartItem getItemByItemId(Long itemId);
    CartItem saveItem(CartItemInDto cartItemInDto);
    void deleteItemId(Long itemId);
    void deleteItemsByCartId(Long cartId);
}
