package com.inventory_service.service;

import com.inventory_service.entity.Inventory;
import com.inventory_service.exception.InventoryNotFoundException;
import com.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;



    public Inventory getInventoryByProductId(String productId) {

        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found for product ID: " + productId));
    }
}
