package com.order_service.orderitem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private Long id;
    private String productId;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
}
