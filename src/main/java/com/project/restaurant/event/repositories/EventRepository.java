package com.project.restaurant.event.repositories;

import com.project.restaurant.event.entities.Event;
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
public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByAvailable(Boolean available, Pageable pageable);

    Event findByEventName(String eventName);

    List<Event> findByManufactureDate(LocalDate currentDate);

    List<Event> findByExpireDate(LocalDate currentDate);
}
