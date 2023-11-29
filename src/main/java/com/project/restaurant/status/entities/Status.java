package com.project.restaurant.status.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a status
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
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the status’s name.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String statusName;
}
