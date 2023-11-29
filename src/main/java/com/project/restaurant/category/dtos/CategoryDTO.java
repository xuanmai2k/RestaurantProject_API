package com.project.restaurant.category.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a category
 *
 * @author xuanmai
 * @since 2023-11-28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {

    /**
     * Represents the category’s id.
     */
    private Long id;

    /**
     * Represents the category’s name.
     */
    private String categoryName;
}
