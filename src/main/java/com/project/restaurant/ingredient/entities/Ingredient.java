package com.project.restaurant.ingredient.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents an ingredient
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
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the ingredient’s name.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String ingredientName;
}
