package com.order_service.order.dto;

import com.order_service.order.entity.OrderState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateState {

    @NotNull(message = "Order state must not be null")
    private OrderState orderState;
}
