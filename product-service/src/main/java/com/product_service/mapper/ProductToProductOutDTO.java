package com.product_service.mapper;

import com.product_service.dto.ProductOutDTO;
import com.product_service.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductOutDTO implements IMapper<Product, ProductOutDTO> {
    @Override
    public ProductOutDTO map(Product product) {
        ProductOutDTO productOutDTO = new ProductOutDTO();

        productOutDTO.setId(product.getId());
        productOutDTO.setName(product.getName());
        productOutDTO.setDescription(product.getDescription());
        productOutDTO.setCategory(product.getCategory());
        productOutDTO.setUnitPrice(product.getUnitPrice());

        return productOutDTO;
    }
}
