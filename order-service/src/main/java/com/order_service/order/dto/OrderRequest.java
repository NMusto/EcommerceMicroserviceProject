package com.order_service.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotNull(message = "UserId is required")
    private Long userId;

    @NotNull(message = "shippingAddress is required")
    private String shippingAddress;

    @NotNull(message = "shippingCity is required")
    private String shippingCity;

    @NotNull(message = "shippingPostalCode is require")
    private String shippingPostalCode;
}
