package com.product_service.mapper;

import com.product_service.dto.ProductRequest;
import com.product_service.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestToProduct implements ProductMapper<ProductRequest, Product> {
    @Override
    public Product map(ProductRequest productRequest) {

        Product product = new Product();

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(productRequest.getCategory());
        product.setUnitPrice(productRequest.getUnitPrice());

        return product;
    }
}
