package com.project.restaurant.ingredient.repositories;

import com.project.restaurant.ingredient.entities.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents an ingredient repository
 * all crud database methods
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Page<Ingredient> findByIngredientNameContaining(String ingredientName, PageRequest pageDTO);

    Ingredient findByIngredientName(String ingredientName);

    boolean existsByIngredientName(String ingredientName);
}
