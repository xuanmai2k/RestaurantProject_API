package com.project.restaurant.cuisine.services;

import com.project.restaurant.category.entities.Category;
import com.project.restaurant.category.repositories.CategoryRepository;
import com.project.restaurant.cuisine.dtos.CreateCuisineDTO;
import com.project.restaurant.cuisine.entities.Cuisine;
import com.project.restaurant.cuisine.entities.CuisineImage;
import com.project.restaurant.cuisine.entities.CuisineIngredient;
import com.project.restaurant.cuisine.repositories.CuisineImageRepository;
import com.project.restaurant.cuisine.repositories.CuisineIngredientRepository;
import com.project.restaurant.cuisine.repositories.CuisineRepository;
import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.event.entities.Event;
import com.project.restaurant.event.repositories.EventRepository;
import com.project.restaurant.ingredient.dtos.IngredientDTO;
import com.project.restaurant.ingredient.entities.Ingredient;
import com.project.restaurant.ingredient.repositories.IngredientRepository;
import com.project.restaurant.mapper.CuisineMapper;
import com.project.restaurant.technique.entities.Technique;
import com.project.restaurant.technique.repositories.TechniqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class CuisineServiceImpl implements CuisineService {
    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TechniqueRepository techniqueRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CuisineImageRepository cuisineImageRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CuisineIngredientRepository cuisineIngredientRepository;
    @Autowired
    private CuisineMapper cuisineMapper;

    @Override
    public Cuisine createCuisine(CreateCuisineDTO createCuisineDTO) {
        Cuisine cuisine = cuisineMapper.toEntity(createCuisineDTO);

        //Find name of technique
        Technique technique = techniqueRepository.findByTechniqueName(createCuisineDTO.getTechniqueDTO().getTechniqueName());
        if(technique == null){
            Technique techniqueNew = new Technique();
            techniqueNew.setTechniqueName(createCuisineDTO.getTechniqueDTO().getTechniqueName());
            technique = techniqueRepository.save(techniqueNew);
        }
        cuisine.setTechnique(technique);

        //Find name of category
        Category category = categoryRepository.findByCategoryName(createCuisineDTO.getCategoryDTO().getCategoryName());
        if (category == null){
            Category categoryNew = new Category();
            categoryNew.setCategoryName(createCuisineDTO.getCategoryDTO().getCategoryName());
            category = categoryRepository.save(categoryNew);
        }
        cuisine.setCategory(category);

        //Find id of event
        if (!createCuisineDTO.getEventDTO().getEventName().isEmpty()){
            Event event = eventRepository.findByEventName(createCuisineDTO.getEventDTO().getEventName());
            cuisine.setEvent(event);
        }

        Cuisine savedCuisine = cuisineRepository.save(cuisine);

        //Save Image
        for (CuisineImage item : createCuisineDTO.getImageList()){
            CuisineImage cuisineImage = new CuisineImage();
            cuisineImage.setCuisine(savedCuisine);
            cuisineImage.setNameImage(item.getNameImage());
            cuisineImage.setImageUrl(item.getImageUrl());
            cuisineImageRepository.save(cuisineImage);
        }

        //Find name of ingredient
        CuisineIngredient cuisineIngredient = new CuisineIngredient();

        for (CuisineIngredient item : createCuisineDTO.getIngredientList()){
            Ingredient ingredient = ingredientRepository.findByIngredientName(item.getIngredient().getIngredientName());
            if (ingredient == null){
                Ingredient ingredientNew = new Ingredient();
                ingredientNew.setIngredientName(item.getIngredient().getIngredientName());
                ingredient = ingredientRepository.save(ingredientNew);
            }
            cuisineIngredient.setCuisine(savedCuisine);
            cuisineIngredient.setIngredient(ingredient);
            cuisineIngredientRepository.save(cuisineIngredient);
        }

        return savedCuisine;
    }

    @Override
    public Page<CreateCuisineDTO> getAllCuisines(PageDTO pageDTO) {
        Page<Cuisine> cuisinePage = cuisineRepository.findAll(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
        List<CreateCuisineDTO> list = new ArrayList<>();

        List<Cuisine> cuisineList = cuisinePage.getContent();
        for (Cuisine item : cuisineList){
            List<CuisineImage> cuisineImageList = cuisineImageRepository.findAllByCuisineId(item.getId());


            CreateCuisineDTO cuisineDTO = cuisineMapper.toCuisineDTO(item);

            cuisineDTO.setImageList(cuisineImageList);


            list.add(cuisineDTO);
        }

        Page<CreateCuisineDTO> page = new PageImpl<>(list, PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()),
                list.size());

        return page;
    }
}
