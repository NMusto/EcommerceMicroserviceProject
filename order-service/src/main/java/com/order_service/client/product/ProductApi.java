package com.order_service.client.product;

import com.order_service.client.product.dto.ProductApiStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductApi {

    @PostMapping("/api/product/check-and-decrease-stock")
    void checkAndDecreaseStock(@RequestBody List<ProductApiStockRequest> productList);
}
