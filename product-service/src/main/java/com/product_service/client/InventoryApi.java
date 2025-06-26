package com.product_service.client;

import com.product_service.client.dto.InventoryApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", fallback = InventoryApiFallback.class)
public interface InventoryApi {

    @GetMapping("/api/inventory/{productId}")
    public InventoryApiResponse getInventoryByProductId(@PathVariable("productId") String productId);
}
