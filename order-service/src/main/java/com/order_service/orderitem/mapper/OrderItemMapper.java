package com.order_service.orderitem.mapper;

import com.order_service.client.dto.CartItemApiResponse;
import com.order_service.orderitem.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toOrderItem(CartItemApiResponse cartItem);

    List<OrderItem> toOrderItemList(List<CartItemApiResponse> cartItems);
}
