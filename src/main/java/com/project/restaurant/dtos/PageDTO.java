package com.project.restaurant.dtos;

import lombok.Data;

/**
 * Represents page
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@Data
public class PageDTO {
    private int pageSize = 10;
    private int pageNumber = 0;
}
