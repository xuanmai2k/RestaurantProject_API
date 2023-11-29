package com.project.restaurant.cuisine.repositories;

import com.project.restaurant.cuisine.entities.CuisineImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisineImageRepository extends JpaRepository<CuisineImage, Long> {
    List<CuisineImage> findAllByCuisineId(Long cuisineId);
}
