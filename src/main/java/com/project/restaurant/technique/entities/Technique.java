package com.project.restaurant.technique.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a technique
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
@Table(name = "technique")
public class Technique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the technique’s name.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String techniqueName;
}
