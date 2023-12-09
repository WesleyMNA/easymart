package com.ecommerce.order.models;

import com.ecommerce.order.dtos.ProductAmqp;
import jakarta.persistence.*;
import lombok.*;

import java.util.Random;
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
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "quantity")
    private Integer quantity;

    public Product(ProductAmqp message) {
        this.id = message.id();
        Random random = new Random();
        this.quantity = random.ints(1, 1000)
                .findFirst()
                .orElse(0);
    }
}
