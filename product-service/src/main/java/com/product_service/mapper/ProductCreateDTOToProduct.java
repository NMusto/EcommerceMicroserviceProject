package com.product_service.mapper;

import com.product_service.dto.ProductCreateDTO;
import com.product_service.dto.ProductUpdateDTO;
import com.product_service.model.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductCreateDTOToProduct implements IMapper<ProductCreateDTO, Product> {
    @Override
    public Product map(ProductCreateDTO productCreateDTO) {

        Product product = new Product();

        product.setName(productCreateDTO.getName());
        product.setDescription(productCreateDTO.getDescription());
        product.setCategory(productCreateDTO.getCategory());
        product.setUnitPrice(productCreateDTO.getUnitPrice());

        return product;
    }
}
