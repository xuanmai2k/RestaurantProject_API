package com.project.restaurant.promotion.controllers;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.dtos.ResponseDTO;
import com.project.restaurant.enums.Response;
import com.project.restaurant.mapper.PromotionMapper;
import com.project.restaurant.promotion.dtos.PromotionDTO;
import com.project.restaurant.promotion.services.PromotionService;
import com.project.restaurant.promotion.entities.Promotion;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Represents a promotion controller
 *
 * @author xuanmai
 * @since 2023-11-24
 */
@RestController
@RequestMapping("${PROMOTION}")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PromotionMapper promotionMapper;

    private final ResponseDTO body = ResponseDTO.getInstance();

    /**
     * Logging in Spring Boot
     */
    Logger logger = LoggerFactory.getLogger(PromotionController.class);

    /**
     * REST API methods for Retrieval operations
     *
     * @param pageDTO This is a page
     * @param status  This is a status of promotions
     * @return list all of promotions
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<?> getAllPromotions(@RequestBody PageDTO pageDTO, @RequestParam(required = false) Boolean status) {
        try {
            Page<PromotionDTO> promotionDTOS;

            if (status != null) {

                //get promotions by status
                promotionDTOS = promotionService.getListPromotionByStatus(status, pageDTO)
                        .map(promotion -> promotionMapper.toPromotionDTO(promotion));
            } else {

                //get all promotions
                promotionDTOS = promotionService.getAllPromotions(pageDTO)
                        .map(promotion -> promotionMapper.toPromotionDTO(promotion));
            }

            //Not empty
            if (!promotionDTOS.isEmpty()) {
                return new ResponseEntity<>(promotionDTOS, HttpStatus.OK);
            }

            // No content
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build create promotion REST API
     *
     * @param promotion This is a promotion
     * @return http status
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<?> createPromotion(@RequestBody @Valid Promotion promotion) {
        try {
            // Check manufacture date <= expire date
            if (promotion.getManufactureDate().isBefore(promotion.getExpireDate())
                    || promotion.getManufactureDate().isEqual(promotion.getExpireDate())) {

                //Set status
                LocalDate currentDate = LocalDate.now();
                if (promotion.getManufactureDate().isEqual(currentDate)) {
                    promotion.setStatus(true);
                } else {
                    promotion.setStatus(false);
                }

                // Save
                promotionService.save(promotion);

                // Successfully
                body.setResponse(Response.Key.STATUS, Response.Value.SUCCESSFULLY);
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            }

            // Invalid value manufacture and expire date
            body.setResponse(Response.Key.STATUS, Response.Value.INVALID_VALUE);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build get promotion by id REST API
     *
     * @param id This is promotion id
     * @return a promotion
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<?> getPromotionById(@PathVariable Long id) {
        try {
            Promotion promotion = promotionService.getPromotionById(id);

            //Found
            if (promotion != null) {
                PromotionDTO promotionDTO = promotionMapper.toPromotionDTO(promotion);
                // Successfully
                return new ResponseEntity<>(promotionDTO, HttpStatus.OK);
            }

            // Not found
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build update promotion REST API
     *
     * @param promotion This is promotion details
     * @param id        This is promotion id
     * @return promotion is updated
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updatePromotion(@RequestBody @Valid Promotion promotion, @PathVariable Long id) {
        try {
            Promotion updatePromotion = promotionService.getPromotionById(id);

            //Found
            if (updatePromotion != null) {

                // Check manufacture date <= expire date
                if (promotion.getManufactureDate().isBefore(promotion.getExpireDate())
                        || promotion.getManufactureDate().isEqual(promotion.getExpireDate())) {

                    Promotion _promotion = updatePromotion;
                    _promotion.setManufactureDate(promotion.getManufactureDate());
                    _promotion.setExpireDate(promotion.getExpireDate());
                    _promotion.setPercentageDiscount(promotion.getPercentageDiscount());
                    _promotion.setMaximumPriceDiscount(promotion.getMaximumPriceDiscount());
                    _promotion.setMinimumOrderValue(promotion.getMinimumOrderValue());

                    //Set status
                    LocalDate currentDate = LocalDate.now();
                    if (promotion.getManufactureDate().isEqual(currentDate)) {
                        _promotion.setStatus(true);
                    } else {
                        _promotion.setStatus(false);
                    }

                    //Successfully
                    return new ResponseEntity<>(promotionService.save(_promotion), HttpStatus.OK);
                }

                // Invalid value manufacture and expire date
                body.setResponse(Response.Key.STATUS, Response.Value.INVALID_VALUE);
                return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
            }

            // Not found
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build delete promotion REST API
     *
     * @param id This is promotion
     * @return http status
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable Long id) {
        try {
            Promotion promotion = promotionService.getPromotionById(id);

            //Found
            if (promotion != null) {

                //Not Used
                if (promotion.getUsed() == 0) {
                    //Found
                    promotionService.delete(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                //Used
                body.setResponse(Response.Key.STATUS, Response.Value.NOT_ACCEPTABLE);
                return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
            }

            //Not Found
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
