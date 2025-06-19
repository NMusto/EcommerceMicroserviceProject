package com.cart_service.service;

import com.cart_service.repository.ICartItemRepository;
import com.cart_service.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final ICartRepository cartRepository;
    private final ICartItemRepository cartItemRepository;
    
}
