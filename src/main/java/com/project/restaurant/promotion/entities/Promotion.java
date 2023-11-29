package com.project.restaurant.promotion.entities;

import com.project.restaurant.utils.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a promotion
 *
 * @author xuanmai
 * @since 2023-11-24
 */
@Data
@Entity
@Table(name = "promotion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * represents the manufacture date of promotion
     */
    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;

    /**
     * represents the expiration date of promotion
     */
    @Column(name = "expire_date")
    private LocalDate expireDate;

    /**
     * Represents the percentage discount
     */
    @Column(name = "percentage_discount")
    @Min(value = 1, message = Constants.PERCENTAGE_PROMOTION)
    @Max(value = 100, message = Constants.PERCENTAGE_PROMOTION)
    @NotNull(message = Constants.NOT_REQUIRE)
    private Integer percentageDiscount;

    /**
     * Represents the maximum price discount
     */
    @Column(name = "maximum_price_discount")
    @Min(value = 1, message = Constants.MAXIMUM_PRICE_DISCOUNT)
    @Max(value = 999999, message = Constants.MAXIMUM_PRICE_DISCOUNT)
    @NotNull(message = Constants.NOT_REQUIRE)
    private Double maximumPriceDiscount;

    /**
     * Represents the minimum order value to add a voucher
     */
    @Column(name = "minimum_order_value")
    @Min(value = 0, message = Constants.MINIMUM_ORDER_VALUE_PROMOTION)
    @Max(value = 999999, message = Constants.MINIMUM_ORDER_VALUE_PROMOTION)
    @NotNull(message = Constants.NOT_REQUIRE)
    private Double minimumOrderValue;

    /**
     * Represents the used promotion
     */
    @Column(name = "used")
    private Integer used = 0;

    /**
     * Represents the status promotion
     */
    @Column(name = "status")
    private Boolean status;

    /**
     * Represents the update at promotion
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedDate;

    /**
     * Represents the creation at promotion
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdDate;

}
