package com.product_service.controller;

import com.product_service.dto.ProductCreateDTO;
import com.product_service.dto.ProductOutDTO;
import com.product_service.dto.ProductUpdateDTO;
import com.product_service.model.Product;
import com.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ProductOutDTO> createProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        ProductOutDTO createdProduct = productService.createProduct(productCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductOutDTO> getProductById(@PathVariable String productId) {
        ProductOutDTO productOutDTO = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productOutDTO);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable String productId,
                                                       @RequestBody ProductUpdateDTO productUpdateDTO) {
        String response = productService.updateProduct(productId, productUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        String response = productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
