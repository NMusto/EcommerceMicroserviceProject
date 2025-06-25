package com.cart_service.dto.cartDto;

import com.cart_service.dto.cartItemDto.CartItemOutDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartOutDto {
    private Long id;
    private Long userId;
    private Double totalAmount;
    private List<CartItemOutDto> items;
}
