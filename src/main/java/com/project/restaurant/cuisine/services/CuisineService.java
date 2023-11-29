package com.project.restaurant.cuisine.services;

import com.project.restaurant.cuisine.dtos.CreateCuisineDTO;
import com.project.restaurant.cuisine.entities.Cuisine;
import com.project.restaurant.dtos.PageDTO;
import org.springframework.data.domain.Page;

public interface CuisineService {
    Cuisine createCuisine(CreateCuisineDTO createCuisineDTO);

    Page<CreateCuisineDTO> getAllCuisines(PageDTO pageDTO);
}
