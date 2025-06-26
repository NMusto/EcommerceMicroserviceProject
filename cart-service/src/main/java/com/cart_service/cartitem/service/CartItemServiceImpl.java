package com.cart_service.cartitem.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductApi productApi;


    @Override
    @Transactional
    public CartItem addItemToCart(Long cartId, CartItemRequest cartItemRequest) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found ID:" + cartId));

        ProductApiResponse productApiResponse = productApi.getProductById(cartItemRequest.getProductId());

        CartItem item = CartItem.builder()
                .cart(cart)
                .productId(cartItemRequest.getProductId())
                .description(productApiResponse.getDescription())
                .unitPrice(productApiResponse.getUnitPrice())
                .quantity(cartItemRequest.getQuantity())
                .subtotal(cartItemRequest.getQuantity() * productApiResponse.getUnitPrice())
                .build();

        cart.getItems().add(item);

        double total = this.recalculateCartTotalAmount(cart);
        cart.setTotalAmount(total);

        cartRepository.save(cart);

        return item;
    }

    @Override
    @Transactional
    public CartItem updateItem(Long itemId, Integer newQuantity) {
        CartItem item = this.getCartItem(itemId);

        item.setQuantity(newQuantity);
        item.setSubtotal(item.getUnitPrice() * newQuantity);

        Cart cart = item.getCart();
        Double total = this.recalculateCartTotalAmount(item.getCart());
        cart.setTotalAmount(total);
        cartRepository.save(cart);

        return cartItemRepository.save(item);
    }

    @Override
    @Transactional
    public void deleteItemById(Long itemId) {
        CartItem item = this.getCartItem(itemId);

        Cart cart = item.getCart();
        double total = this.recalculateCartTotalAmount(cart);
        cart.setTotalAmount(total);

        cart.getItems().remove(item);   // orphanRemoval = true

        cartRepository.save(cart);
    }

    @Override
    public CartItem getItemById(Long itemId) {
        return this.getCartItem(itemId);
    }

    @Override
    public List<CartItem> getItemsByCartId(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found ID: " + cartId));

        return cartItemRepository.findByCart(cart);
    }

    @Override
    public double recalculateCartTotalAmount(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getSubtotal())
                .sum();
    }

    public CartItem getCartItem(Long itemId) {
        return cartItemRepository.findById(itemId)
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found ID: " + itemId));
    }
}
