package com.ecommerce.cart.models;

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
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "catalog_id", nullable = false)
    private Long catalogId;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Product(Long userId,
                   Long catalogId,
                   String title,
                   Float price,
                   Integer quantity) {
        this.userId = userId;
        this.catalogId = catalogId;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }
}
