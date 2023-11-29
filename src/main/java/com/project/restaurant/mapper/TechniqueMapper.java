package com.project.restaurant.mapper;

import com.project.restaurant.technique.dtos.TechniqueDTO;
import com.project.restaurant.technique.entities.Technique;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechniqueMapper {
    TechniqueDTO toTechniqueDTO(Technique technique);

    @Mapping(target = "id", ignore = true)
    Technique toEntity(TechniqueDTO techniqueDTO);
}
