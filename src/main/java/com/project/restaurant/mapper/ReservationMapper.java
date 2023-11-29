package com.project.restaurant.mapper;

import com.project.restaurant.reservation.dtos.ReservationDTO;
import com.project.restaurant.reservation.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationDTO toReservationDTO(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    Reservation toEntity(ReservationDTO reservationDTO);
}
