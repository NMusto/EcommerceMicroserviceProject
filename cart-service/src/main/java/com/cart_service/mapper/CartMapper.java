package com.cart_service.mapper;

import com.cart_service.dto.cartDto.CartOutDto;
import com.cart_service.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartOutDto toCartOutDto(Cart cart);
}
