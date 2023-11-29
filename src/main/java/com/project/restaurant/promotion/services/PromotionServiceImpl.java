package com.project.restaurant.promotion.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.promotion.entities.Promotion;
import com.project.restaurant.promotion.repositories.PromotionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a promotion service
 *
 * @author xuanmai
 * @since 2023-11-24
 */
@Service
@Transactional
@Component
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    /**
     * This method is used to get all list promotions
     *
     * @param pageDTO This is a page
     * @return list all of promotions
     */
    @Override
    public Page<Promotion> getAllPromotions(PageDTO pageDTO) {
        return promotionRepository.findAll(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }

    /**
     * This method is used to get list promotions follow by status
     *
     * @param status  This is a status of promotion
     * @param pageDTO This is a page
     * @return list all of promotions
     */
    @Override
    public Page<Promotion> getListPromotionByStatus(Boolean status, PageDTO pageDTO) {
        return promotionRepository.findByStatus(status, PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }

    /**
     * This method is used to get a promotion base on id
     *
     * @param id This is promotion id
     * @return promotion base on id
     */
    @Override
    public Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to save a promotion
     *
     * @param promotion This is a promotion
     * @return a promotion
     */
    @Override
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    /**
     * This method is used to delete a promotion by id
     *
     * @param id This is promotion id
     */
    @Override
    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }

    /**
     * Get all promotions which manufactureDate is today to change the status
     */
    @Override
    public void updateActivatePromotionStatus() {
        LocalDate currentDate = LocalDate.now();

        List<Promotion> promotions = promotionRepository.findByManufactureDate(currentDate);

        for (Promotion promotion : promotions) {
            promotion.setStatus(true);
            save(promotion);
        }
    }

    /**
     * Get all promotions which expireDate is today to change the status
     */
    @Override
    public void updateExpirePromotionStatus() {
        LocalDate currentDate = LocalDate.now();

        List<Promotion> promotions = promotionRepository.findByExpireDate(currentDate);

        for (Promotion promotion : promotions) {
            promotion.setStatus(false);
            save(promotion);
        }
    }

}
