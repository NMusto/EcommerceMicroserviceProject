package com.product_service.client;

import com.product_service.dto.InventoryDTO;
import org.springframework.stereotype.Component;

@Component
public class InventoryApiFallback implements IInventoryApi{

    @Override
    public InventoryDTO getInventoryByProductId(String productId) {

        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setProductId(productId);
        inventoryDTO.setId("fallback");
        inventoryDTO.setStock(-9999);

        return inventoryDTO;
    }
}
