package com.ecommerce.cart.repositories;

import com.ecommerce.cart.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByUserIdAndCatalogId(Long userId, Long catalogId);

    Page<Product> findByUserId(Long userId, Pageable pageable);
}
