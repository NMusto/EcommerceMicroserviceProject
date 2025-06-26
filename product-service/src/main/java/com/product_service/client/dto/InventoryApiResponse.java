package com.product_service.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryApiResponse {

    private String id;
    private String productId;
    private Integer stock;
}
