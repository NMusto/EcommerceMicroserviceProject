package com.product_service.client;

import com.product_service.dto.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", fallback = InventoryApiFallback.class)
public interface IInventoryApi {

    @GetMapping("/api/inventory/{productId}")
    public InventoryDTO getInventoryByProductId(@PathVariable("productId") String productId);
}
