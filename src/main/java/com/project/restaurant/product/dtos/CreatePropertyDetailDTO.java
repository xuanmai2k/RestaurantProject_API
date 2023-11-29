package com.project.restaurant.product.dtos;

import com.project.restaurant.product.entities.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePropertyDetailDTO {
    private List<Property> propertyList;
}
