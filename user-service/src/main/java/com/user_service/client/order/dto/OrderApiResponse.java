package com.user_service.client.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderApiResponse {
    private Long id;
    private Long userId;
    private LocalDate createdAt;
    private OrderState orderState;
    private String shippingAddress;
    private String shippingCity;
    private String shippingPostalCode;
    private List<OrderItemApiResponse> items;
    private Double totalAmount;
}
