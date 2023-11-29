package com.project.restaurant.product.repositories;

import com.project.restaurant.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductsById(Long id);
}