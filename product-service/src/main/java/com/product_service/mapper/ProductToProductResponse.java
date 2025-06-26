package com.product_service.mapper;

import com.product_service.dto.ProductResponse;
import com.product_service.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductResponse implements ProductMapper<Product, ProductResponse> {
    @Override
    public ProductResponse map(Product product) {
        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategory(product.getCategory());
        productResponse.setUnitPrice(product.getUnitPrice());

        return productResponse;
    }
}
