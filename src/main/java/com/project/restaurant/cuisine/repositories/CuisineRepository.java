package com.project.restaurant.cuisine.repositories;

import com.project.restaurant.cuisine.entities.Cuisine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {
    @Query("SELECT c FROM Cuisine c " +
            "INNER JOIN CuisineImage ci ON c.id = ci.cuisine.id " +
            "INNER JOIN CuisineIngredient cin ON c.id = cin.cuisine.id")
    Page<Cuisine> findAll(Pageable pageable);

    @Query("SELECT c FROM Cuisine c " +
            "INNER JOIN CuisineImage ci ON c.id = ci.cuisine.id " +
            "INNER JOIN CuisineIngredient cin ON c.id = cin.cuisine.id " +
    "WHERE c.id = :id ")
    Optional<Cuisine> findById(@Param("id") Long id);
}
