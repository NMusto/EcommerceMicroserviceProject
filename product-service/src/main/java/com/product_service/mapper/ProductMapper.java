package com.product_service.mapper;

public interface ProductMapper<I,O> {
    public O map(I in);
}
