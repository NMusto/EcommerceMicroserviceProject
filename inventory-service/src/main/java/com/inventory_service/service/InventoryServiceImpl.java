package com.inventory_service.service;

import com.inventory_service.entity.Inventory;
import com.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl {

    private final InventoryRepository inventoryRepository;



    public Inventory getInventoryByProductId(String productId) {

        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for product ID: " + productId));
    }
}
