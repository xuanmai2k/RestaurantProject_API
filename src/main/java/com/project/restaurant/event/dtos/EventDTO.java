package com.project.restaurant.event.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents an event
 *
 * @author xuanmai
 * @since 2023-11-28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDTO {
    /**
     * Represents the event’s id.
     */
    private Long id;

    /**
     * Represents the event’s name.
     */
    private String eventName;

    /**
     * represents the manufacture date of event
     */
    private LocalDate manufactureDate;

    /**
     * represents the expiration date of event
     */
    private LocalDate expireDate;

    /**
     * Represents the event’s description.
     */
    private String description;

    /**
     * Represents the percentage discount
     */
    private Integer percentageDiscount;
}
