package com.product_service.exception;

public class DuplicateProductNameException extends RuntimeException{

    public DuplicateProductNameException(String message) {
        super(message);
    }
}
