package com.ecommerce.order.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "em_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "catalog_id", nullable = false)
    private Long catalogId;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "total", nullable = false)
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
