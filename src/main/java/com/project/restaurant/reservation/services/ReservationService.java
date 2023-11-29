package com.project.restaurant.reservation.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.reservation.dtos.SearchReservationDTO;
import com.project.restaurant.reservation.entities.Reservation;
import org.springframework.data.domain.Page;

public interface ReservationService {
    /**
     * This method is used to get all reservations
     *
     * @param pageDTO This is a page
     * @return list all of reservations
     */
    Page<Reservation> getAllReservation(PageDTO pageDTO);

    /**
     * This method is used to create a reservation
     *
     * @param reservation This is a reservation
     */
    public Reservation save(Reservation reservation);

    /**
     * This method is used to get a reservation base on id
     *
     * @param id This is reservation id
     * @return reservation base on id
     */
    Reservation getReservationById(Long id);

    /**
     * This method is used to delete a reservation by id
     *
     * @param id This is reservation id
     */
    void delete(Long id);

    /**
     * This method is used to find reservation by keyword
     *
     * @param searchReservationDTO This is keyword
     * @return List of reservations
     */
    Page<Reservation> search(SearchReservationDTO searchReservationDTO);
}
