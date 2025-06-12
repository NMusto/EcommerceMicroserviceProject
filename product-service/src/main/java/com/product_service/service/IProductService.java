package com.product_service.service;

import com.product_service.dto.ProductCreateDTO;
import com.product_service.dto.ProductUpdateDTO;
import com.product_service.dto.ProductOutDTO;
import com.product_service.model.Product;

import java.util.List;

public interface IProductService {

    ProductOutDTO createProduct(ProductCreateDTO ProductCreateDTO);

    String updateProduct(String productId, ProductUpdateDTO productUpdateDTO);

    ProductOutDTO getProductById(String productId);

    List<Product> getAllProducts();

    String deleteProduct(String productId);

    Product getProduct(String productId);

    void updateStock(String productId, Integer stock);
}
