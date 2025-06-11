package com.product_service.mapper;

import com.product_service.dto.ProductInDTO;
import com.product_service.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductInDTOToProduct implements IMapper<ProductInDTO, Product> {
    @Override
    public Product map(ProductInDTO productInDTO) {

        Product product = new Product();
        product.setName(productInDTO.getName());
        product.setDescription(productInDTO.getDescription());
        product.setCategory(productInDTO.getCategory());
        product.setUnitPrice(productInDTO.getUnitPrice());

        return product;
    }
}
