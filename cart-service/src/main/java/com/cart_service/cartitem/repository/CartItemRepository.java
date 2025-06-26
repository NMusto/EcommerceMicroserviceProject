package com.cart_service.cartitem.repository;

import com.cart_service.cart.entity.Cart;
import com.cart_service.cartitem.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);
}
