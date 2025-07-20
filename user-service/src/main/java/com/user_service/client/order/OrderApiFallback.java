package com.user_service.client.order;

import com.user_service.client.order.dto.OrderApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderApiFallback implements OrderApi{

    @Override
    public OrderApiResponse getOrderByUserId(Long userId) {
        return null;
    }
}
