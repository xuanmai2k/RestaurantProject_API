package com.project.restaurant.technique.repositories;

import com.project.restaurant.technique.entities.Technique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Represents a technique repository
 * all crud database methods
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Repository
public interface TechniqueRepository extends JpaRepository<Technique, Long> {

    Page<Technique> findByTechniqueNameContaining(String techniqueName, PageRequest pageDTO);

    Technique findByTechniqueName(String techniqueName);

    boolean existsByTechniqueName(String techniqueName);
}
