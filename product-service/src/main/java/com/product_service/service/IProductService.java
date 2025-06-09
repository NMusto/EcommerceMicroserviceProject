package com.product_service.service;

import com.product_service.dto.ProductInDTO;
import com.product_service.dto.ProductOutDTO;
import com.product_service.model.Product;

import java.util.List;

public interface IProductService {

    ProductOutDTO createProduct(ProductInDTO productInDTO);

    ProductOutDTO updateProduct(Long productId, ProductInDTO productInDTO);

    ProductOutDTO getProductById(Long productId);

    List<ProductOutDTO> getAllProducts();

    void deleteProduct(Long productId);
}
