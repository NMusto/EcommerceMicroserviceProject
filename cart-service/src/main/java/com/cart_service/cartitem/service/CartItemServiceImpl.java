package com.cart_service.cartitem.service;

import com.cart_service.cartitem.dto.CartItemResponse;
import com.cart_service.cartitem.dto.CartItemUpdateRequest;
import com.cart_service.cartitem.mapper.CartItemMapper;
import com.cart_service.client.ProductApi;
import com.cart_service.cartitem.dto.CartItemRequest;
import com.cart_service.client.dto.ProductApiResponse;
import com.cart_service.cart.entity.Cart;
import com.cart_service.cartitem.entity.CartItem;
import com.cart_service.cartitem.repository.CartItemRepository;
import com.cart_service.cart.repository.CartRepository;
import com.cart_service.exception.CartItemNotFoundException;
import com.cart_service.exception.CartNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductApi productApi;
    private final CartItemMapper cartItemMapper;



    @Override
    public List<CartItemResponse> getItemsByCartId(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found ID: " + cartId));

        return cartItemRepository.findByCart(cart).stream()
                .map(cartItemMapper::toCartItemResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemResponse getItemById(Long itemId) {
        CartItem cartItem = this.getCartItem(itemId);
        return cartItemMapper.toCartItemResponse(cartItem);
    }

    @Override
    @Transactional
    public CartItemResponse addItemToCart(Long cartId, CartItemRequest cartItemRequest) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found ID:" + cartId));

        ProductApiResponse productApiResponse = productApi.getProductById(cartItemRequest.getProductId());

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .productId(cartItemRequest.getProductId())
                .description(productApiResponse.getDescription())
                .unitPrice(productApiResponse.getUnitPrice())
                .quantity(cartItemRequest.getQuantity())
                .subtotal(cartItemRequest.getQuantity() * productApiResponse.getUnitPrice())
                .build();

        cartItemRepository.save(cartItem);

        cart.getItems().add(cartItem);

        double total = this.recalculateCartTotalAmount(cart);
        cart.setTotalAmount(total);

        cartRepository.save(cart);

        return cartItemMapper.toCartItemResponse(cartItem);
    }

    @Override
    @Transactional
    public CartItemResponse updateItem(Long itemId, CartItemUpdateRequest cartItemUpdateRequest) {
        CartItem cartItem = this.getCartItem(itemId);

        cartItem.setQuantity(cartItemUpdateRequest.getQuantity());
        cartItem.setSubtotal(cartItem.getUnitPrice() * cartItemUpdateRequest.getQuantity());
        cartItemRepository.save(cartItem);

        Cart cart = cartItem.getCart();
        Double total = this.recalculateCartTotalAmount(cartItem.getCart());
        cart.setTotalAmount(total);
        cartRepository.save(cart);

        return cartItemMapper.toCartItemResponse(cartItem);
    }

    @Override
    @Transactional
    public String deleteItemById(Long itemId) {
        CartItem item = this.getCartItem(itemId);

        Cart cart = item.getCart();
        cart.getItems().remove(item);   // orphanRemoval = true

        double total = this.recalculateCartTotalAmount(cart);
        cart.setTotalAmount(total);

        cartRepository.save(cart);
        return "CartItem with ID " + itemId + " was successfully deleted";
    }

    private double recalculateCartTotalAmount(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getSubtotal())
                .sum();
    }

    private CartItem getCartItem(Long itemId) {
        return cartItemRepository.findById(itemId)
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found ID: " + itemId));
    }
}
