package com.cart_service.dto;

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
