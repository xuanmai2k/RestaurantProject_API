package com.project.restaurant.cuisine.repositories;

import com.project.restaurant.cuisine.entities.CuisineIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisineIngredientRepository extends JpaRepository<CuisineIngredient, Long> {
    List<CuisineIngredient> findAllByCuisineId(Long cuisineId);
}
