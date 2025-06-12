package com.inventory_service.service;

import com.inventory_service.model.Inventory;

public interface IInventoryService {

    Inventory getInventoryByProductId(String productId);
}
