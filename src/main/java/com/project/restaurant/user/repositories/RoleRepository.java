package com.project.restaurant.user.repositories;

import java.util.Optional;

import com.project.restaurant.user.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository also extends JpaRepository and provides a finder method
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);
}
