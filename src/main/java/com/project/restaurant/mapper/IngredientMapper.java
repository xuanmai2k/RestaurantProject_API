package com.project.restaurant.mapper;

import com.project.restaurant.ingredient.dtos.IngredientDTO;
import com.project.restaurant.ingredient.entities.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientDTO toIngredientDTO(Ingredient ingredient);

    @Mapping(target = "id", ignore = true)
    Ingredient toEntity(IngredientDTO ingredientDTO);
}
