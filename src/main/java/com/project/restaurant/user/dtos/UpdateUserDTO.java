package com.project.restaurant.user.dtos;

import lombok.Data;

import java.time.LocalDate;

/**
 * This class is used to update User
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Data
public class UpdateUserDTO {
    private String fullName;
    private String phoneNumber;
    private String gender;
    private LocalDate dateOfBirth;
}
