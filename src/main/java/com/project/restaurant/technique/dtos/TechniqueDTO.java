package com.project.restaurant.technique.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a technique
 *
 * @author xuanmai
 * @since 2023-11-28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechniqueDTO {
    private Long id;

    /**
     * Represents the technique’s name.
     */
    private String techniqueName;
}
