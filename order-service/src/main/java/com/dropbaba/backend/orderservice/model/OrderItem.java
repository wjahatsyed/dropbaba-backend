package com.dropbaba.backend.orderservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int quantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
