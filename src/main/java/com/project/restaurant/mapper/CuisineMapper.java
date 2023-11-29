package com.project.restaurant.mapper;

import com.project.restaurant.cuisine.dtos.CreateCuisineDTO;
import com.project.restaurant.cuisine.entities.Cuisine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CuisineMapper {

    @Mapping(source ="technique", target = "techniqueDTO")
    @Mapping(source ="category", target = "categoryDTO")
    @Mapping(source ="event", target = "eventDTO")
    CreateCuisineDTO toCuisineDTO(Cuisine cuisine);

    @Mapping(source ="techniqueDTO", target = "technique")
    @Mapping(source ="categoryDTO", target = "category")
    @Mapping(source ="eventDTO", target = "event")
    @Mapping(target = "id", ignore = true)
    Cuisine toEntity(CreateCuisineDTO createCuisineDTO);



}
