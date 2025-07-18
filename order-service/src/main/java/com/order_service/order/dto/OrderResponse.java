package com.order_service.order.dto;

import com.order_service.order.entity.OrderState;
import com.order_service.orderitem.dto.OrderItemResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;
    private Long userId;
    private LocalDate createdAt;
    private OrderState orderState;
    private String shippingAddress;
    private String shippingCity;
    private String shippingPostalCode;
    private List<OrderItemResponse> items;
    private Double totalAmount;
}
