package com.project.restaurant.promotion.repositories;

import com.project.restaurant.promotion.entities.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a promotion repository
 * all crud database methods
 *
 * @author xuanmai
 * @since 2023-11-24
 */
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Page<Promotion> findByStatus(Boolean status, Pageable pageable);

    List<Promotion> findByManufactureDate(LocalDate currentDate);

    List<Promotion> findByExpireDate(LocalDate currentDate);

}
