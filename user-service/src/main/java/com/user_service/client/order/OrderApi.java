package com.user_service.client.order;

import com.user_service.client.order.dto.OrderApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service", fallback = OrderApiFallback.class)
public interface OrderApi {

    @GetMapping("/api/order/user/{userId}")
    List<OrderApiResponse> getOrdersByUserId(@PathVariable("userId") Long userId);
}
