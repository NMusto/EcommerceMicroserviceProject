package com.order_service.orderitem.entity;

import com.order_service.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "order")
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_id")
    private String productId;

    private String description;

    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    private Double subtotal;
}
