package com.user_service.exception;

public class OrderServiceUnavailableException extends RuntimeException{

    public OrderServiceUnavailableException(String message) {
        super(message);
    }
}
