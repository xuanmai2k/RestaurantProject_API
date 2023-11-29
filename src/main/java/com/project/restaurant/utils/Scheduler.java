package com.project.restaurant.utils;

import com.project.restaurant.event.services.EventService;
import com.project.restaurant.promotion.services.PromotionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private EventService eventService;

    /**
     * Get all promotions which expireDate is today to change the status
     */
    @PostConstruct
    @Scheduled(cron = "59 59 23 * * *")  // Run at 23h59m59s everyday
    public void updateExpireStatusPromotion() {
        System.out.println("update expire promotion");
        promotionService.updateExpirePromotionStatus();
    }

    /**
     * Get all promotions which manufactureDate is today to change the status
     */
    @PostConstruct
    @Scheduled(cron = "0 0 0 * * *")  // Run at 0h everyday
    public void updateActivateStatusPromotion() {
        System.out.println("update activate promotion");
        promotionService.updateActivatePromotionStatus();
    }

    /**
     * Get all events which expireDate is today to change the available
     */
    @PostConstruct
    @Scheduled(cron = "59 59 23 * * *")  // Run at 23h59m59s everyday
    public void updateExpireAvailableEvent() {
        System.out.println("update expire event");
        eventService.updateUnavailableEvent();
    }

    /**
     * Get all events which manufacturerDate is today to change the available
     */
    @PostConstruct
    @Scheduled(cron = "0 0 0 * * *")  // Run at 0h everyday
    public void updateAvailableEvent() {
        System.out.println("update available event");
        eventService.updateAvailableEvent();
    }
}
