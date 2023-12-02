package com.ecommerce.catalog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "em_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "price", nullable = false)
    private Float price;

    public Product(String title, Float price) {
        this.title = title;
        this.price = price;
    }
}
