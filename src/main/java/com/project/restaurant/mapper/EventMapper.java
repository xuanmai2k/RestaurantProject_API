package com.project.restaurant.mapper;

import com.project.restaurant.event.dtos.EventDTO;
import com.project.restaurant.event.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toEventDTO(Event event);

    @Mapping(target = "id", ignore = true)
    Event toEntity(EventDTO eventDTO);
}
