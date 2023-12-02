package com.ecommerce.catalog.repositories;

import com.ecommerce.catalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Boolean existsByTitle(String title);
}
