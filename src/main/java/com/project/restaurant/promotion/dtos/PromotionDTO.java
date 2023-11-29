package com.project.restaurant.promotion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents a promotion
 *
 * @author xuanmai
 * @since 2023-11-28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromotionDTO {
    private Long id;

    /**
     * represents the manufacture date of promotion
     */
    private LocalDate manufactureDate;

    /**
     * represents the expiration date of promotion
     */
    private LocalDate expireDate;

    /**
     * Represents the percentage discount
     */
    private Integer percentageDiscount;

    /**
     * Represents the maximum price discount
     */
    private Double maximumPriceDiscount;

    /**
     * Represents the minimum order value to add a voucher
     */
    private Double minimumOrderValue;
}
