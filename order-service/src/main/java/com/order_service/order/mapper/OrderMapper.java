package com.order_service.order.mapper;

import com.order_service.order.dto.OrderRequest;
import com.order_service.order.dto.OrderResponse;
import com.order_service.order.entity.Order;
import com.order_service.orderitem.mapper.OrderItemMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

    OrderResponse toOrderResponse(Order order);

    Order toEntity(OrderRequest orderRequest);
}
