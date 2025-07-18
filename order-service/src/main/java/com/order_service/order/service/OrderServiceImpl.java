package com.order_service.order.service;

import com.order_service.client.CartApi;
import com.order_service.client.dto.CartApiResponse;
import com.order_service.exception.OrderNotFoundException;
import com.order_service.order.dto.OrderRequest;
import com.order_service.order.dto.OrderResponse;
import com.order_service.order.dto.OrderUpdateRequest;
import com.order_service.order.dto.OrderUpdateState;
import com.order_service.order.entity.Order;
import com.order_service.order.entity.OrderState;
import com.order_service.order.mapper.OrderMapper;
import com.order_service.order.repository.OrderRepository;
import com.order_service.orderitem.entity.OrderItem;
import com.order_service.orderitem.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartApi cartApi;
    private final OrderItemMapper orderItemMapper;


    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = this.getOrder(orderId);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse getOrderByUserId(Long userId) {
        Order order = orderRepository.findByUserId(userId)
                .orElseThrow(() -> new OrderNotFoundException("User ID: " + userId + " not found!"));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        CartApiResponse cart = cartApi.getCartByUserId(orderRequest.getUserId());

        Order order = orderMapper.toEntity(orderRequest);

        order.setCreatedAt(LocalDateTime.now());
        order.setOrderState(OrderState.PENDING);
        order.setUserId(orderRequest.getUserId());
        order.setTotalAmount(cart.getTotalAmount());

        List<OrderItem> orderItems = cart.getItems().stream()
                        .map(item -> {
                            OrderItem orderItem = orderItemMapper.toOrderItem(item);
                            orderItem.setOrder(order);
                            return orderItem;
                        })
                                .collect(Collectors.toList());

        order.setItems(orderItems);
        orderRepository.save(order);

        cartApi.clearCart(orderRequest.getUserId());

        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse updateOrder(Long orderId, OrderUpdateRequest orderUpdateRequest) {
        Order order = this.getOrder(orderId);

        if (orderUpdateRequest.getShippingAddress() != null) {
            order.setShippingAddress(orderUpdateRequest.getShippingAddress());
        }
        if (orderUpdateRequest.getShippingCity() != null) {
            order.setShippingCity(orderUpdateRequest.getShippingCity());
        }
        if (orderUpdateRequest.getShippingPostalCode() != null) {
            order.setShippingPostalCode(orderUpdateRequest.getShippingPostalCode());
        }

        orderRepository.save(order);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse updateOrderState(Long orderId, OrderUpdateState orderUpdateState) {
        Order order = this.getOrder(orderId);
        order.setOrderState(orderUpdateState.getOrderState());
        orderRepository.save(order);

        return orderMapper.toOrderResponse(order);
    }

    @Override
    public String deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }
        orderRepository.deleteById(orderId);
        return "Order with ID " + orderId + " was successfully deleted";
    }

    private Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
    }
}
