package com.project.restaurant.reservation.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Represents a reservation
 *
 * @author xuanmai
 * @since 2023-11-24
 */
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation")
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the customer’s name.
     */
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    /**
     * Represents the customer’s phone.
     */
    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;

    /**
     * Represents the customer’s booking time.
     */
    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;

    /**
     * Represents the number of customer.
     */
    @Column(name = "diners", nullable = false)
    private Integer diners;

    /**
     * Represents the number of table.
     */
    @Column(name = "table_number")
    private Integer tableNumber;

    /**
     * Represents the status of reservation.
     */
    @Column(name = "status")
    private String status;

    /**
     * Represents the update at reservation
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedDate;

    /**
     * Represents the creation at reservation
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdDate;

}
