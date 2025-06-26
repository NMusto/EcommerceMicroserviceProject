package com.cart_service.cart.mapper;

import com.cart_service.cart.dto.CartResponse;
import com.cart_service.cart.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartResponse toCartOutDto(Cart cart);
}
