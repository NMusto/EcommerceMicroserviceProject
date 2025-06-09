package com.product_service.service;

import com.product_service.dto.ProductInDTO;
import com.product_service.dto.ProductOutDTO;
import com.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;


    @Override
    public ProductOutDTO createProduct(ProductInDTO productInDTO) {
        return null;
    }

    @Override
    public ProductOutDTO updateProduct(Long productId, ProductInDTO productInDTO) {
        return null;
    }

    @Override
    public ProductOutDTO getProductById(Long productId) {
        return null;
    }

    @Override
    public List<ProductOutDTO> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }
}
