package com.cart_service.cartitem.entity;

import com.cart_service.cart.entity.Cart;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "product_id")
    private Long productId;

    private String description;

    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    private Double subtotal;

}
