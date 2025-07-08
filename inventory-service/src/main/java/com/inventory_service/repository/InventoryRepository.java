package com.inventory_service.repository;

import com.inventory_service.entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    Optional<Inventory> findByProductId(String productId);

    void deleteByProductId(String productId);
}
