package com.product_service.repository;

import com.product_service.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    boolean existsByNameIgnoreCase(String name);
}
