package com.project.restaurant.ingredient.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.ingredient.entities.Ingredient;
import org.springframework.data.domain.Page;

public interface IngredientService {
    /**
     * This method is used to get all ingredients
     *
     * @param pageDTO This is a page
     * @return list all of ingredients
     */
    Page<Ingredient> getAllIngredients(PageDTO pageDTO);

    /**
     * This method is used to save an ingredient
     *
     * @param ingredient This is an ingredient
     */
    Ingredient save(Ingredient ingredient);

    /**
     * This method is used to get an ingredient base on id
     *
     * @param id This is ingredient id
     * @return ingredient base on id
     */
    Ingredient getIngredientById(Long id);

    /**
     * This method is used to delete an ingredient by id
     *
     * @param id This is ingredient id
     */
    void delete(Long id);

    /**
     * This method is used to check ingredient name existence
     *
     * @param ingredientName This is ingredient name
     * @return boolean
     */
    boolean checkForExistence(String ingredientName);

    /**
     * This method is used to find ingredient by keyword
     *
     * @param keyword This is keyword
     * @return List of ingredients
     */
    Page<Ingredient> search(String keyword, PageDTO pageDTO);
}
