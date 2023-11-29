package com.project.restaurant.ingredient.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an ingredient
 *
 * @author xuanmai
 * @since 2023-11-28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientDTO {
    /**
     * Represents the ingredient’s id.
     */
    private Long id;

    /**
     * Represents the ingredient’s name.
     */
    private String ingredientName;
}
