package com.project.restaurant.category.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.category.entities.Category;
import com.project.restaurant.category.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * This method is used to list all categories
     *
     * @param pageDTO This is a page
     * @return list all of categories
     */
    @Override
    public Page<Category> getAllCategories(PageDTO pageDTO) {
        return categoryRepository.findAll(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }

    /**
     * This method is used to save a category
     *
     * @param category This is a category
     * @return a category
     */
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * This method is used to get a category base on id
     *
     * @param id This is category id
     * @return category base on id
     */
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to delete a category by id
     *
     * @param id This is category id
     */
    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    /**
     * This method is used to check category name existence
     *
     * @param categoryName This is category name
     * @return boolean
     */
    @Override
    public boolean checkForExistence(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }

    /**
     * This method is used to find category by keyword
     *
     * @param keyword This is keyword
     * @return List of categories
     */
    @Override
    public Page<Category> search(String keyword, PageDTO pageDTO) {
        return categoryRepository.findByCategoryNameContaining(keyword, PageRequest.of(pageDTO.getPageNumber(),
                pageDTO.getPageSize()));
    }
}
