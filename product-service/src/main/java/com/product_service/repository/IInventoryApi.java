package com.product_service.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface IInventoryApi {

    @GetMapping()
    public Integer getStockByProductId(@PathVariable)
}
