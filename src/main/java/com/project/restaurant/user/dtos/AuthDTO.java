package com.project.restaurant.user.dtos;

import lombok.Data;

/**
 * Represents login feature
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Data
public class AuthDTO {
    /**
     * Username to log in
     */
    private String email;

    /**
     * Password to log in
     */
    private String password;
}
