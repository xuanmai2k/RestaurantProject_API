package com.project.restaurant.user.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Models for Authorization (Role).
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Entity
@Table(name = "roles")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 50, unique = true)
  @NonNull
  private String name;
}