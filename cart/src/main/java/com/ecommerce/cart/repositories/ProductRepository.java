package com.ecommerce.cart.repositories;

import com.ecommerce.cart.models.Product;
import com.ecommerce.cart.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByUserAndCatalogId(User user, Long catalogId);

    Page<Product> findByUser(User user, Pageable pageable);
}
