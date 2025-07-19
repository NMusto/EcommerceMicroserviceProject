package com.user_service.client.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartApiResponse {
    private Long id;
    private Long userId;
    private Double totalAmount;
    private List<CartItemApiResponse> items;
}
