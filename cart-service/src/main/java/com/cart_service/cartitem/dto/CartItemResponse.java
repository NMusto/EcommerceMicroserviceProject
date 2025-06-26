package com.cart_service.cartitem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private Long id;
    private Long productId;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
    private Long cartId;
}
