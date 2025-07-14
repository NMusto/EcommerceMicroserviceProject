package com.cart_service.cart.mapper;

import com.cart_service.cart.dto.CartRequest;
import com.cart_service.cart.dto.CartResponse;
import com.cart_service.cart.entity.Cart;
import com.cart_service.cartitem.mapper.CartItemMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

// uses = CartItemMapper.class:
// Inform MapStruct to use CartItemMapper when mapping List<CartItem> field to List<CartItemResponse> (cart.id -> cartId)
@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper {


    CartResponse toCartResponse(Cart cart);

    Cart toEntity(CartRequest cartRequest);

    // Include CartRequest as a parameter so that this method is only applied after the toEntity(CartRequest) mapping,
    // and not to other mappings that return a Cart.
    @AfterMapping
    default void afterToEntityMapping(@MappingTarget Cart cart) {
        cart.setItems(new ArrayList<>());
        cart.setTotalAmount(0.0);
    }
}
