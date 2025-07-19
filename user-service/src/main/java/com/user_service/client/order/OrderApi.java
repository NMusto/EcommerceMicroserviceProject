package com.user_service.client.order;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "order-service", fallback = OrderApiFallback.class)
public interface OrderApi {


}
