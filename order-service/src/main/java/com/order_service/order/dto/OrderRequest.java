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

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Shipping address is required")
    private String shippingAddress;

    @NotNull(message = "Shipping city is required")
    private String shippingCity;

    @NotNull(message = "Shipping postal code is require")
    private String shippingPostalCode;
}
