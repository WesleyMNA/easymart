package com.ecommerce.order.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    @Column(name = "dateHour", nullable = false)
    private LocalDateTime dateHour;
    @Column(name = "total", nullable = false)
    private Integer total;

    @ToString.Exclude
    @OneToMany(mappedBy = "order")
    private Set<Product> products;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
