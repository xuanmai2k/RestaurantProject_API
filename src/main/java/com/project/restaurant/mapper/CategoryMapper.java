package com.project.restaurant.mapper;

import com.project.restaurant.category.dtos.CategoryDTO;
import com.project.restaurant.category.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toCategoryDTO(Category category);

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryDTO categoryDTO);
}
