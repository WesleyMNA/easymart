package com.ecommerce.order.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "em_order_product")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "total", nullable = false)
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
