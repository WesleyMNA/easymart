package com.ecommerce.order.models;

import com.ecommerce.order.dtos.ProductAmqp;
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
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "total", nullable = false)
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public Product(ProductAmqp message) {
        this.id = message.id();
        this.title = message.title();
        this.price = message.price();
    }
}
