package com.product_service.client;

import com.product_service.client.dto.InventoryApiResponse;
import com.product_service.exception.InventoryServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
public class InventoryApiFallback implements InventoryApi {

    @Override
    public InventoryApiResponse getInventoryByProductId(String productId) {
        throw new InventoryServiceUnavailableException("Inventory service is unavailable. Failed to get inventory with product ID: " + productId );
    }
}
