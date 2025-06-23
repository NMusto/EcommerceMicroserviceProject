package com.cart_service.service;

import com.cart_service.dto.CartItemInDto;
import com.cart_service.model.CartItem;
import com.cart_service.repository.ICartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService{

    private final ICartItemRepository cartItemRepository;


    @Override
    public List<CartItem> getItemsByCartId(Long cartId) {
        return null;
    }

    @Override
    public CartItem getItemByItemId(Long itemId) {
        return null;
    }

    @Override
    public CartItem saveItem(CartItemInDto cartItemInDto) {
        return null;
    }

    @Override
    public void deleteItemId(Long itemId) {

    }

    @Override
    public void deleteItemsByCartId(Long cartId) {

    }
}
