package com.ecommerce.order.models;

import com.ecommerce.order.enumerations.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "em_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "dateHour", nullable = false)
    private LocalDateTime dateHour;
    @Column(name = "total", nullable = false)
    private Float total;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderProduct> products = new HashSet<>();

    public Order(Long userId, LocalDateTime dateHour, OrderStatus status) {
        this.userId = userId;
        this.dateHour = dateHour;
        this.status = status;
    }

    public void setProducts(Collection<OrderProduct> products) {
        this.products.addAll(products);
    }
}
