package com.inventory_service.kafka.consumer;

import com.inventory_service.kafka.event.ProductDeletedEvent;
import com.inventory_service.kafka.event.StockEvent;
import com.inventory_service.entity.Inventory;
import com.inventory_service.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    private final InventoryRepository inventoryRepository;

    public KafkaConsumer(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @KafkaListener(topics = "stock-topic", groupId = "inventory-group")
    public void consumeStockEvent(StockEvent stockEvent) {

        log.info("Stock event received: {}", stockEvent);

        inventoryRepository.findByProductId(stockEvent.getProductId())
                .ifPresentOrElse(
                        inventory -> {
                            inventory.setStock(stockEvent.getStock());
                            inventoryRepository.save(inventory);
                            log.info("Inventory updated for product ID: {}", stockEvent.getProductId());
                        },
                        () -> {
                            Inventory newInventory = new Inventory();
                            newInventory.setProductId(stockEvent.getProductId());
                            newInventory.setStock(stockEvent.getStock());
                            inventoryRepository.save(newInventory);
                            log.info("New inventory created for product ID: {}", stockEvent.getProductId());
                        }
                );
    }

    @KafkaListener(topics = "product-delete-topic", groupId = "inventory-group")
    public void consumeProductDeletedEvent(ProductDeletedEvent event) {

        log.info("consumeProductDeletedEvent received: {}", event);

        inventoryRepository.deleteByProductId(event.getProductId());

        log.info("Inventory deleted for product Id: {}", event.getProductId());
    }

}
