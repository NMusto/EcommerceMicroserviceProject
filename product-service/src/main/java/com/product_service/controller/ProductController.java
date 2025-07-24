package com.product_service.controller;

import com.product_service.dto.ProductRequest;
import com.product_service.dto.ProductResponse;
import com.product_service.dto.ProductStockRequest;
import com.product_service.dto.ProductUpdateRequest;
import com.product_service.entity.Product;
import com.product_service.service.ProductService;
import com.product_service.service.ProductServiceImpl;
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


    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String productId) {
        ProductResponse productResponse = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse createdProduct = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PostMapping("/check-and-decrease-stock")
    public ResponseEntity<Void> checkAndDecreaseStock(@RequestBody List<ProductStockRequest> productList) {
        productService.checkAndDecreaseStock(productList);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/increase-stock")
    public ResponseEntity<Void> increaseStock(@RequestBody List<ProductStockRequest> productList) {
        productService.increaseStock(productList);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable String productId,
                                                       @RequestBody ProductUpdateRequest productUpdateRequest) {
        String response = productService.updateProduct(productId, productUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        String response = productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
