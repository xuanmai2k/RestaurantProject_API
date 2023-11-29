package com.project.restaurant.reservation.repositories;

import com.project.restaurant.reservation.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Represents a reservation repository
 * all crud database methods
 *
 * @author xuanmai
 * @since 2023-11-24
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r " +
            "WHERE " +
            "(:status = '' OR r.status LIKE %:status% ) " +
            "AND (:keyword = '' OR r.customerName LIKE %:keyword% ) " +
            "OR (:keyword = '' OR r.customerPhone LIKE %:keyword% ) " +
            "AND (:bookingTime = r.bookingTime OR :bookingTime IS NULL) ")
    Page<Reservation> searchReservation(@Param("keyword") String keyword,
                                        @Param("bookingTime") LocalDateTime bookingTime,
                                        @Param("status") String status,
                                        Pageable pageable);
}
