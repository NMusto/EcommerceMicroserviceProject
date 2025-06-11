package com.product_service.mapper;

public interface IMapper<I,O> {
    public O map(I in);
}
