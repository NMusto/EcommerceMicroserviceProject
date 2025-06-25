package com.cart_service.service.cartService;

import com.cart_service.dto.cartDto.CartOutDto;
import com.cart_service.dto.cartDto.CartiInDto;
import com.cart_service.dto.cartDto.UpdateCartDto;
import com.cart_service.mapper.CartMapper;
import com.cart_service.model.Cart;
import com.cart_service.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final ICartRepository cartRepository;
    private final CartMapper cartMapper;


    @Override
    public List<CartOutDto> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(cartMapper::toCartOutDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartOutDto getCartById(Long cartId) {
        Cart cart = this.getCart(cartId);
        return cartMapper.toCartOutDto(cart);
    }

    @Override
    public CartOutDto getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User ID: " + userId + " not found!"));
        return cartMapper.toCartOutDto(cart);
    }

    @Override
    public CartOutDto createCart(CartiInDto cartiInDto) {
        Cart cart = cartRepository.findByUserId(cartiInDto.getUserId())
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .userId(cartiInDto.getUserId())
                                .items(new ArrayList<>())
                                .totalAmount(0.0)
                                .build()
                ));
        return cartMapper.toCartOutDto(cart);
    }

    @Override
    public CartOutDto updateCart(Long cartId, UpdateCartDto updateCartDto) {
        Cart cart = this.getCart(cartId);
        cart.setTotalAmount(updateCartDto.getTotalAmount());
        cartRepository.save(cart);

        return cartMapper.toCartOutDto(cart);
    }

    @Override
    public void deleteCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new RuntimeException("Cart not found with ID: " + cartId);
        }
        cartRepository.deleteById(cartId);
    }

    @Override
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartId));
    }


}
