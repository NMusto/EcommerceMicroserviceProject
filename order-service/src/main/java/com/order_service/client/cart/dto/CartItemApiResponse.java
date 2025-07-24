package com.order_service.client.cart.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemApiResponse {
    private String productId;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
}
