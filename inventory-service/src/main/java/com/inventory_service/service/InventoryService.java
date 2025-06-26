package com.inventory_service.service;

import com.inventory_service.entity.Inventory;

public interface InventoryService {

    Inventory getInventoryByProductId(String productId);
}
