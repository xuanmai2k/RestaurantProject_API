package com.project.restaurant.category.controllers;

import com.project.restaurant.category.dtos.CategoryDTO;
import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.dtos.ResponseDTO;
import com.project.restaurant.enums.Response;
import com.project.restaurant.category.entities.Category;
import com.project.restaurant.category.services.CategoryService;
import com.project.restaurant.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Represents a category controller
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@RestController
@RequestMapping("${CATEGORY}")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    private final ResponseDTO body = ResponseDTO.getInstance();

    /**
     * Logging in Spring Boot
     */
    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    /**
     * REST API methods for Retrieval operations
     *
     * @param pageDTO This is a page
     * @return list all of categories
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<?> getAllCategory(@RequestBody PageDTO pageDTO) {
        try {
            Page<CategoryDTO> categoryDTOS = categoryService.getAllCategories(pageDTO)
                    .map(category -> categoryMapper.toCategoryDTO(category));

            //Not empty
            if (!categoryDTOS.isEmpty()) {
                return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
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
     * Build create category REST API
     *
     * @param category This is a category
     * @return a category is inserted into database
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            // Check existence
            if (categoryService.checkForExistence(category.getCategoryName()) == false) {
                categoryService.save(category);

                // Successfully
                body.setResponse(Response.Key.STATUS, Response.Value.SUCCESSFULLY);
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            }

            // Duplicate categoryName
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
     * Build get category by id REST API
     *
     * @param id This is category id
     * @return a category
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable long id) {
        try {
            Category category = categoryService.getCategoryById(id);

            // Found
            if (category != null) {
                // Successfully
                return new ResponseEntity<>(category, HttpStatus.OK);
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
     * Build update category REST API
     *
     * @param id       This is category id
     * @param category This category details
     * @return category is updated
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @RequestBody Category category) {
        try {
            Category updateCategory = categoryService.getCategoryById(id);

            // Found
            if (updateCategory != null) {

                // Check existence
                if (categoryService.checkForExistence(category.getCategoryName()) == false) {
                    // Update
                    Category _category = updateCategory;
                    _category.setCategoryName(category.getCategoryName());

                    // Successfully
                    return new ResponseEntity<>(categoryService.save(_category), HttpStatus.OK);
                }

                // Duplicate categoryName
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
     * Build delete category REST API
     *
     * @param id This is category
     * @return http status
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id) {
        try {
            Category category = categoryService.getCategoryById(id);

            // Found
            if (category != null) {

                // Delete
                categoryService.delete(id);

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
     * Build search category REST API
     *
     * @param keyword This is keyword
     * @return http status
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/search")
    public ResponseEntity<?> searchCategory(@RequestBody PageDTO pageDTO, @RequestParam String keyword) {
        try {
            Page<CategoryDTO> categoryPage = categoryService.search(keyword, pageDTO)
                    .map(category -> categoryMapper.toCategoryDTO(category));

            //Not found
            if (categoryPage.isEmpty()) {
                //No content
                body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
                return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
            }

            //Found
            return new ResponseEntity<>(categoryPage, HttpStatus.OK);

        } catch (Exception ex) {
            logger.info(ex.getMessage());

            //Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
