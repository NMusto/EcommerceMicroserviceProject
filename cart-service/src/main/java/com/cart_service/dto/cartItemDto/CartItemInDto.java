package com.cart_service.dto.cartItemDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemInDto {

    private Long userId;
    private Long productId;
    private Integer quantity;
}
