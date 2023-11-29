package com.project.restaurant.user.services;

/**
 * Represents Email Service
 * @author xuanmai
 * @since 2023-11-22
 */
public interface EmailService {

    /**
     * This method is used to sendEmail
     *
     */
    public void sendEmail(String to, String subject, String text);
}

