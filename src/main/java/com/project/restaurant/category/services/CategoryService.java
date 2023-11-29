package com.project.restaurant.category.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.category.entities.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {

    /**
     * This method is used to get all categories
     *
     * @param pageDTO This is a page
     * @return list all of categories
     */
    Page<Category> getAllCategories(PageDTO pageDTO);

    /**
     * This method is used to create a category
     *
     * @param category This is a category
     */
    Category save(Category category);

    /**
     * This method is used to get a category base on id
     *
     * @param id This is category id
     * @return category base on id
     */
    Category getCategoryById(Long id);

    /**
     * This method is used to delete a category by id
     *
     * @param id This is category id
     */
    void delete(Long id);

    /**
     * This method is used to check category name existence
     *
     * @param categoryName This is category name
     * @return boolean
     */
    boolean checkForExistence(String categoryName);

    /**
     * This method is used to find category by keyword
     *
     * @param keyword This is keyword
     * @return List of categories
     */
    Page<Category> search(String keyword, PageDTO pageDTO);
}
