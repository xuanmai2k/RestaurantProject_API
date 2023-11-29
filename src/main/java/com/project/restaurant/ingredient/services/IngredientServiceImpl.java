package com.project.restaurant.ingredient.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.ingredient.entities.Ingredient;
import com.project.restaurant.ingredient.repositories.IngredientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    /**
     * This method is used to get all ingredients
     *
     * @param pageDTO This is a page
     * @return list all of ingredients
     */
    @Override
    public Page<Ingredient> getAllIngredients(PageDTO pageDTO) {
        return ingredientRepository.findAll(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }

    /**
     * This method is used to save an ingredient
     *
     * @param ingredient This is an ingredient
     */
    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    /**
     * This method is used to get an ingredient base on id
     *
     * @param id This is ingredient id
     * @return ingredient base on id
     */
    @Override
    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to delete an ingredient by id
     *
     * @param id This is ingredient id
     */
    @Override
    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }

    /**
     * This method is used to check ingredient name existence
     *
     * @param ingredientName This is ingredient name
     * @return boolean
     */
    @Override
    public boolean checkForExistence(String ingredientName) {
        return ingredientRepository.existsByIngredientName(ingredientName);
    }

    /**
     * This method is used to find ingredient by keyword
     *
     * @param keyword This is keyword
     * @return List of ingredients
     */
    @Override
    public Page<Ingredient> search(String keyword, PageDTO pageDTO) {
        return ingredientRepository.findByIngredientNameContaining(keyword,
                PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }
}
