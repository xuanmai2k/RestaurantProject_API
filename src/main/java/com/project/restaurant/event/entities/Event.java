package com.project.restaurant.event.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an event
 *
 * @author xuanmai
 * @since 2023-11-24
 */
@Data
@Entity
@Table(name = "event")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the event’s name.
     */
    @Column(name = "name")
    private String eventName;

    /**
     * represents the manufacture date of event
     */
    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;

    /**
     * represents the expiration date of event
     */
    @Column(name = "expire_date")
    private LocalDate expireDate;

    /**
     * Represents the event’s description.
     */
    @Column(name = "description")
    private String description;

    /**
     * Represents the percentage discount
     */
    @Column(name = "percentage_discount")
    private Integer percentageDiscount;

    /**
     * Represents the status event
     */
    @Column(name = "available")
    private Boolean available;

    /**
     * Represents the update at event
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedDate;

    /**
     * Represents the creation at event
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdDate;

}
