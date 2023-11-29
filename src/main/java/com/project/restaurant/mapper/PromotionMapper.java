package com.project.restaurant.mapper;

import com.project.restaurant.promotion.dtos.PromotionDTO;
import com.project.restaurant.promotion.entities.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    PromotionDTO toPromotionDTO(Promotion promotion);

    @Mapping(target = "id", ignore = true)
    Promotion toEntity(PromotionDTO promotionDTO);
}
