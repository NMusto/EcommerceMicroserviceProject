package com.product_service.mapper;

import com.product_service.dto.ProductInDTO;
import com.product_service.model.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductInDTOToProduct implements IMapper<ProductInDTO, Product> {
    @Override
    public Product map(ProductInDTO productInDTO) {

        Product product = new Product();

        Optional.ofNullable(productInDTO.getName()).ifPresent(product::setName);
        Optional.ofNullable(productInDTO.getDescription()).ifPresent(product::setDescription);
        Optional.ofNullable(productInDTO.getCategory()).ifPresent(product::setCategory);
        Optional.ofNullable(productInDTO.getUnitPrice()).ifPresent(product::setUnitPrice);

        return product;
    }
}
