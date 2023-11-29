package com.project.restaurant.reservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a reservation
 *
 * @author xuanmai
 * @since 2023-11-28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {
    private Long id;

    /**
     * Represents the customer’s name.
     */
    private String customerName;

    /**
     * Represents the customer’s phone.
     */
    private String customerPhone;

    /**
     * Represents the customer’s booking time.
     */
    private LocalDateTime bookingTime;

    /**
     * Represents the number of customer.
     */
    private Integer diners;

    /**
     * Represents the number of table.
     */
    private Integer tableNumber;

    /**
     * Represents the status of reservation.
     */
    private String status;
}
