package com.user_service.client.order;

import com.user_service.client.order.dto.OrderApiResponse;
import com.user_service.exception.OrderServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderApiFallback implements OrderApi{

    @Override
    public List<OrderApiResponse> getOrdersByUserId(Long userId) {
        log.error("Fallback activated: getOrderByUserId failed for userId {}", userId);
        throw new OrderServiceUnavailableException("Order service is unavailable. Cannot retrieve Order for user ID: " + userId);
    }
}
