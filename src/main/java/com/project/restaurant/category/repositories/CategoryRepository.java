package com.project.restaurant.category.repositories;

import com.project.restaurant.category.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Represents a category repository
 * all crud database methods
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findByCategoryNameContaining(String categoryName, PageRequest pageDTO);

    Category findByCategoryName(String category);

    boolean existsByCategoryName(String categoryName);
}
