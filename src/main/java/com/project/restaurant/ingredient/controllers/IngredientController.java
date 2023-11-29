package com.project.restaurant.ingredient.controllers;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.dtos.ResponseDTO;
import com.project.restaurant.enums.Response;
import com.project.restaurant.ingredient.dtos.IngredientDTO;
import com.project.restaurant.ingredient.entities.Ingredient;
import com.project.restaurant.ingredient.services.IngredientService;
import com.project.restaurant.mapper.IngredientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Represents an ingredient controller
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@RestController
@RequestMapping("${INGREDIENT}")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientMapper ingredientMapper;

    private final ResponseDTO body = ResponseDTO.getInstance();

    /**
     * Logging in Spring Boot
     */
    Logger logger = LoggerFactory.getLogger(IngredientController.class);

    /**
     * REST API methods for Retrieval operations
     *
     * @param pageDTO This is a page
     * @return list all of ingredients
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<?> getAllIngredient(@RequestBody PageDTO pageDTO) {
        try {
            Page<IngredientDTO> ingredientPage = ingredientService.getAllIngredients(pageDTO)
                    .map(ingredient -> ingredientMapper.toIngredientDTO(ingredient));

            //Not empty
            if (!ingredientPage.isEmpty()) {
                return new ResponseEntity<>(ingredientPage, HttpStatus.OK);
            }

            // No content
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build create ingredient REST API
     *
     * @param ingredient This is an ingredient
     * @return an ingredient is inserted into database
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createIngredient(@RequestBody Ingredient ingredient) {
        try {

            // Check existence
            if (ingredientService.checkForExistence(ingredient.getIngredientName()) == false) {
                ingredientService.save(ingredient);

                // Successfully
                body.setResponse(Response.Key.STATUS, Response.Value.SUCCESSFULLY);
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            }

            // Duplicate ingredientName
            body.setResponse(Response.Key.STATUS, Response.Value.DUPLICATED);
            return new ResponseEntity<>(body, HttpStatus.CONFLICT);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Build get ingredient by id REST API
     *
     * @param id This is ingredient id
     * @return an ingredient
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable long id) {
        try {
            Ingredient ingredient = ingredientService.getIngredientById(id);

            // Found
            if (ingredient != null) {
                // Successfully
                return new ResponseEntity<>(ingredient, HttpStatus.OK);
            }

            // Not found
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build update ingredient REST API
     *
     * @param id         This is ingredient id
     * @param ingredient This ingredient details
     * @return ingredient is updated
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateIngredient(@PathVariable long id, @RequestBody Ingredient ingredient) {
        try {
            Ingredient updateIngredient = ingredientService.getIngredientById(id);

            // Found
            if (updateIngredient != null) {

                // Check existence
                if (ingredientService.checkForExistence(ingredient.getIngredientName()) == false) {

                    // Update
                    Ingredient _ingredient = updateIngredient;
                    _ingredient.setIngredientName(ingredient.getIngredientName());

                    // Successfully
                    return new ResponseEntity<>(ingredientService.save(_ingredient), HttpStatus.OK);
                }

                // Duplicate ingredientName
                body.setResponse(Response.Key.STATUS, Response.Value.DUPLICATED);
                return new ResponseEntity<>(body, HttpStatus.CONFLICT);
            }

            // Not found
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build delete ingredient REST API
     *
     * @param id This is ingredient
     * @return http status
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable long id) {
        try {
            Ingredient ingredient = ingredientService.getIngredientById(id);

            // Found
            if (ingredient != null) {

                // Delete
                ingredientService.delete(id);

                //Successfully
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // Not found
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build search ingredient REST API
     *
     * @param keyword This is keyword
     * @return list of ingredients involving keyword
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/search")
    public ResponseEntity<?> searchIngredient(@RequestBody PageDTO pageDTO, @RequestParam String keyword) {
        try {
            Page<IngredientDTO> ingredientPage = ingredientService.search(keyword, pageDTO)
                    .map(ingredient -> ingredientMapper.toIngredientDTO(ingredient));

            //Not found
            if (ingredientPage.isEmpty()) {
                //No content
                body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
                return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
            }

            //Found
            return new ResponseEntity<>(ingredientPage, HttpStatus.OK);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            //Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
