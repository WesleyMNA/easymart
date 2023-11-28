package com.ecommerce.cart.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "em_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private Set<Product> products;

    public void setProducts(Product product) {
        product.setUser(this);
        this.products.add(product);
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
