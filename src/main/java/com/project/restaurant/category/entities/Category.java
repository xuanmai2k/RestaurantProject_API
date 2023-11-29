package com.project.restaurant.category.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a category
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the category’s name.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String categoryName;
}
