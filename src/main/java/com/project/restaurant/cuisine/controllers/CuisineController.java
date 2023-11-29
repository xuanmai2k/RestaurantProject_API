package com.project.restaurant.cuisine.controllers;

import com.project.restaurant.cuisine.dtos.CreateCuisineDTO;
import com.project.restaurant.cuisine.entities.Cuisine;
import com.project.restaurant.cuisine.services.CuisineService;
import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.dtos.ResponseDTO;
import com.project.restaurant.enums.Response;
import com.project.restaurant.mapper.CuisineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${cuisine}")
public class CuisineController {
    @Autowired
    private CuisineService cuisineService;
    @Autowired
    private CuisineMapper cuisineMapper;

    private final ResponseDTO body = ResponseDTO.getInstance();

    /**
     * Logging in Spring Boot
     */
    Logger logger = LoggerFactory.getLogger(CuisineController.class);

    /**
     * REST API methods for Retrieval operations
     *
     * @param pageDTO This is a page
     * @return list all of cuisines
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<?> getAllCuisine(@RequestBody PageDTO pageDTO) {
        try {
            Page<CreateCuisineDTO> cuisineDTOS = cuisineService.getAllCuisines(pageDTO);

            //Not empty
            if (!cuisineDTOS.isEmpty()) {
                return new ResponseEntity<>(cuisineDTOS, HttpStatus.OK);
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
     * Build get cuisine by id REST API
     *
     * @param id This is cuisine id
     * @return a cuisine
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<?> getCuisineById(@PathVariable Long id) {
        try {
            CreateCuisineDTO cuisine = cuisineService.getCuisineById(id);

            // Found
            if (cuisine != null) {
                // Successfully
                return new ResponseEntity<>(cuisine, HttpStatus.OK);
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

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCuisine(@RequestBody CreateCuisineDTO createCuisineDTO) {
        try {
            Cuisine saved = cuisineService.createCuisine(createCuisineDTO);

            //Successfully
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            //Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build update cuisine REST API
     *
     * @param id               This is cuisine id
     * @param createCuisineDTO This cuisine details
     * @return cuisine is updated
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateCuisine(@PathVariable Long id, @RequestBody CreateCuisineDTO createCuisineDTO) {
        try {
            CreateCuisineDTO cuisine = cuisineService.getCuisineById(id);

            //Found
            if(cuisine != null){
                Cuisine updatedCuisine = cuisineService.updateCuisine(id, createCuisineDTO);

                //Successfully
                return new ResponseEntity<>(updatedCuisine, HttpStatus.OK);
            }

            // Not found
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            //Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
