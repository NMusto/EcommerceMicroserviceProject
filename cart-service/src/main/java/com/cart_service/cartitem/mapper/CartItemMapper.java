package com.cart_service.cartitem.mapper;

import com.cart_service.cartitem.dto.CartItemResponse;
import com.cart_service.cartitem.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemResponse toCartItemResponse(CartItem cartItem);
}
