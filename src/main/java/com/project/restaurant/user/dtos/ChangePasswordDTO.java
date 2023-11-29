package com.project.restaurant.user.dtos;

import lombok.Data;

/**
 * This class is used to change password
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Data
public class ChangePasswordDTO {
    private String oldPassword;
    private String newPassword;
    private String email;
    private String otpCode;
}
