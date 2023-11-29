package com.project.restaurant.promotion.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.promotion.entities.Promotion;
import org.springframework.data.domain.Page;

/**
 * Represents a promotion service
 *
 * @author xuanmai
 * @since 2023-11-24
 */
public interface PromotionService {

    /**
     * This method is used to get all promotions
     *
     * @param pageDTO This is a page
     * @return list all of promotions
     */
    Page<Promotion> getAllPromotions(PageDTO pageDTO);

    /**
     * This method is used to get list promotions by status
     *
     * @param status  This is a status of promotion
     * @param pageDTO This is a page
     * @return list of promotions by status
     */
    Page<Promotion> getListPromotionByStatus(Boolean status, PageDTO pageDTO);

    /**
     * This method is used to get a promotion base on id
     *
     * @param id This is promotion id
     * @return promotion base on id
     */
    Promotion getPromotionById(Long id);

    /**
     * This method is used to save a promotion
     *
     * @param promotion This is a promotion
     */
    Promotion save(Promotion promotion);

    /**
     * This method is used to delete a promotion by id
     *
     * @param id This is promotion id
     */
    void delete(Long id);

    /**
     * This method is used to update activate status of promotion
     */
    void updateActivatePromotionStatus();

    /**
     * This method is used to update expire status of promotion
     */
    void updateExpirePromotionStatus();

}
