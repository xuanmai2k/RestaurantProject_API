package com.project.restaurant.product.repositories;

import com.project.restaurant.product.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Boolean existsByProductCode(String productCode);
}
