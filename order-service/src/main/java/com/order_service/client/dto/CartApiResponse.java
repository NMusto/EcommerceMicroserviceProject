package com.order_service.client.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartApiResponse {
    private Long id;
    private Long userId;
    private Double totalAmount;
    private List<CartItemApiResponse> items;
}
