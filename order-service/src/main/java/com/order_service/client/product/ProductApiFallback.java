package com.order_service.client.product;

import com.order_service.client.product.dto.ProductApiStockRequest;
import com.order_service.exception.CartServiceUnavailableException;
import com.order_service.exception.ProductServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProductApiFallback implements ProductApi{


    @Override
    public void checkAndDecreaseStock(List<ProductApiStockRequest> productList) {
        log.error("Fallback activated: checkAndDecreaseStock failed connection");
        throw new ProductServiceUnavailableException("Product service is unavailable. Cannot check and decrease stock");
    }

    @Override
    public void increaseStock(List<ProductApiStockRequest> productList) {
        log.error("Fallback activated: increaseStock failed connection");
        throw new ProductServiceUnavailableException("Product service is unavailable. Cannot increase stock");
    }
}
