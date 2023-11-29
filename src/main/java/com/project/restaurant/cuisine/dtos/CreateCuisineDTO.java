package com.project.restaurant.cuisine.dtos;

import com.project.restaurant.category.dtos.CategoryDTO;
import com.project.restaurant.cuisine.entities.CuisineImage;
import com.project.restaurant.cuisine.entities.CuisineIngredient;
import com.project.restaurant.event.dtos.EventDTO;
import com.project.restaurant.ingredient.dtos.IngredientDTO;
import com.project.restaurant.ingredient.entities.Ingredient;
import com.project.restaurant.technique.dtos.TechniqueDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCuisineDTO {
    private String cuisineName;
    private String description;
    private Double price;
    private TechniqueDTO techniqueDTO;
    private CategoryDTO categoryDTO;
    private EventDTO eventDTO;
    private List<CuisineIngredient> ingredientList;
    private List<CuisineImage> imageList;
    private Boolean available = true;
}
