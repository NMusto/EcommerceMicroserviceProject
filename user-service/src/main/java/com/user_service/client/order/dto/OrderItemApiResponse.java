package com.user_service.client.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemApiResponse {
    private String productId;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
}
