package com.order_service.order.service;

import com.order_service.order.dto.OrderRequest;
import com.order_service.order.dto.OrderResponse;
import com.order_service.order.dto.OrderUpdateRequest;
import com.order_service.order.dto.OrderUpdateState;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long orderId);
    OrderResponse getOrderByUserId(Long userId);
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse updateOrder(Long orderId, OrderUpdateRequest orderUpdateRequest);
    OrderResponse updateOrderState(Long orderId, OrderUpdateState orderUpdateState);
    String deleteOrder(Long orderId);
}
