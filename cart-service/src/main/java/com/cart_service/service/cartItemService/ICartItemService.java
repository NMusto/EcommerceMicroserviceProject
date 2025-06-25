package com.cart_service.service.cartItemService;

import com.cart_service.dto.cartItemDto.CartItemInDto;
import com.cart_service.model.Cart;
import com.cart_service.model.CartItem;

import java.util.List;

public interface ICartItemService {

    CartItem addItemToCart(Long cartId, CartItemInDto cartItemInDto);
    CartItem updateItem(Long itemId, Integer newQuantity);
    void deleteItemById(Long itemId);
    CartItem getItemById(Long itemId);
    List<CartItem> getItemsByCartId(Long cartId);
    double recalculateCartTotalAmount(Cart cart);


}
