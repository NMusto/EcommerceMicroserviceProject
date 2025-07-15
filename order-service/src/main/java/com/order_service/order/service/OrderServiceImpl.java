package com.order_service.order.service;

import com.order_service.order.dto.OrderRequest;
import com.order_service.order.dto.OrderResponse;
import com.order_service.order.dto.OrderUpdateRequest;
import com.order_service.order.dto.OrderUpdateState;
import com.order_service.order.entity.Order;
import com.order_service.order.mapper.OrderMapper;
import com.order_service.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Override
    public List<OrderResponse> getAllOrders() {
        return null;
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = this.getOrder(orderId);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse getOrderByUserId(Long userId) {
        Order order = orderRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User ID: " + userId + " not found!"));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        return null;
    }

    @Override
    public OrderResponse updateOrder(Long orderId, OrderUpdateRequest orderUpdateRequest) {
        return null;
    }

    @Override
    public OrderResponse updateOrderState(Long orderId, OrderUpdateState orderUpdateState) {
        return null;
    }

    @Override
    public String deleteOrder(Long orderId) {
        return null;
    }

    private Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }
}
