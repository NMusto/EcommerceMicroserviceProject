package com.product_service.exception;

public class InventoryServiceUnavailableException extends RuntimeException{

    public InventoryServiceUnavailableException(String message) {
        super(message);
    }
}
