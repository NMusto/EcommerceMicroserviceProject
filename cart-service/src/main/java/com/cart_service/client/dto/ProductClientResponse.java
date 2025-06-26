package com.cart_service.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductClientResponse {
    private String id;
    private String name;
    private String description;
    private Double unitPrice;
    private String category;
    private Integer stock;
}
