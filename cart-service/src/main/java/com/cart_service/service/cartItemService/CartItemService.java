package com.cart_service.service.cartItemService;

import com.cart_service.client.IProductApi;
import com.cart_service.dto.cartItemDto.CartItemInDto;
import com.cart_service.dto.productDto.ProductDto;
import com.cart_service.model.Cart;
import com.cart_service.model.CartItem;
import com.cart_service.repository.ICartItemRepository;
import com.cart_service.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final ICartItemRepository cartItemRepository;
    private final ICartRepository cartRepository;
    private final IProductApi productApi;


    @Override
    public CartItem addItemToCart(Long cartId, CartItemInDto cartItemInDto) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        ProductDto productDto = productApi.getProductById(cartItemInDto.getProductId());

        CartItem item = CartItem.builder()
                .cart(cart)
                .productId(cartItemInDto.getProductId())
                .description(productDto.getDescription())
                .unitPrice(productDto.getUnitPrice())
                .quantity(cartItemInDto.getQuantity())
                .subtotal(cartItemInDto.getQuantity() * productDto.getUnitPrice())
                .build();

        cart.getItems().add(item);

        double total = this.recalculateCartTotalAmount(cart);
        cart.setTotalAmount(total);

        cartRepository.save(cart);

        return item;
    }

    @Override
    public CartItem updateItem(Long itemId, Integer newQuantity) {
        return null;
    }

    @Override
    public void deleteItemById(Long itemId) {

    }

    @Override
    public CartItem getItemById(Long itemId) {
        return null;
    }

    @Override
    public List<CartItem> getItemsByCartId(Long cartId) {
        return null;
    }

    @Override
    public double recalculateCartTotalAmount(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getSubtotal())
                .sum();
    }

}
