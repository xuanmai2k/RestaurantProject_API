package com.project.restaurant.user.dtos;

import lombok.Data;

/**
 * Represents create, update address
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Data
public class AddressDTO {
    private String name;
    private String phoneNumber;
    private String deliveryAddress;
    private String province;
    private String district;
    private String ward;
    private String label;
    private String isDefault;
}
