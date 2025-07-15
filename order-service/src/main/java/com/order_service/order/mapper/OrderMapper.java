package com.order_service.order.mapper;

import com.order_service.order.dto.OrderResponse;
import com.order_service.order.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toOrderResponse(Order order);
}
