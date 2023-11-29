package com.project.restaurant.status.repositories;

import com.project.restaurant.status.entities.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents a status repository
 * all crud database methods
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    Page<Status> findByStatusNameContaining(String statusName, PageRequest pageDTO);

    boolean existsByStatusName(String statusName);
}
