package com.product_service.client;

import com.product_service.client.dto.InventoryApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryApiFallback implements InventoryApi {

    @Override
    public InventoryApiResponse getInventoryByProductId(String productId) {

        InventoryApiResponse inventoryApiResponse = new InventoryApiResponse();
        inventoryApiResponse.setProductId(productId);
        inventoryApiResponse.setId("fallback");
        inventoryApiResponse.setStock(-9999);

        log.info("Fallback activated");

        return inventoryApiResponse;
    }
}
