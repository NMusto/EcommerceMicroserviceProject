package com.cart_service.cart.dto;

import com.cart_service.cartitem.dto.CartItemResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private Long id;
    private Long userId;
    private Double totalAmount;
    private List<CartItemResponse> items;
}
