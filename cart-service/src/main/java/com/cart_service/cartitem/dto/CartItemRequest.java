package com.cart_service.cartitem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {

    @NotBlank(message = "ProductId is required")
    private Long productId;

    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;
}
