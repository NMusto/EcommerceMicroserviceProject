package com.order_service.order.controller;

import com.order_service.order.dto.OrderRequest;
import com.order_service.order.dto.OrderResponse;
import com.order_service.order.dto.OrderUpdateRequest;
import com.order_service.order.dto.OrderUpdateState;
import com.order_service.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<OrderResponse> getOrderByUserId(@PathVariable Long userId) {
        OrderResponse orderResponse = orderService.getOrderByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long orderId,
                                                   @RequestBody OrderUpdateRequest orderUpdateRequest) {
        OrderResponse orderResponse = orderService.updateOrder(orderId, orderUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrderState(@PathVariable Long orderId,
                                                   @RequestBody OrderUpdateState orderUpdateState) {
        OrderResponse orderResponse = orderService.updateOrderState(orderId, orderUpdateState);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        String response = orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
