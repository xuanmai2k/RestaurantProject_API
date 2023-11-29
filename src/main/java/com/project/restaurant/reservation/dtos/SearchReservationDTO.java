package com.project.restaurant.reservation.dtos;

import com.project.restaurant.dtos.PageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Custom response status
 *
 * @author xuanmai
 * @since 2023-11-27
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchReservationDTO {
    /**
     * Represents the customer name or phone.
     */
    private String keyword = "";

    /**
     * Represents the customer’s booking time.
     */
    private LocalDateTime bookingTime;

    /**
     * Represents the status of reservation.
     */
    private String status = "";

    /**
     * Represents page
     */
    private PageDTO pageDTO;
}
