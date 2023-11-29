package com.project.restaurant.user.dtos;

import lombok.Data;


/**
 * This class is used to register a user
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Data
public class RegisterDTO {
    /**
     * Represents the email
     */
    private String email;

    /**
     * Represents the password
     */
    private String fullName;

    /**
     * Represents the mobile number
     */
    private String password;

    /**
     * Represents the otp code
     */
    private String otpCode;
}

