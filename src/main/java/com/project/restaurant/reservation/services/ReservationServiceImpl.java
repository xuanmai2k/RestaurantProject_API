package com.project.restaurant.reservation.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.reservation.dtos.SearchReservationDTO;
import com.project.restaurant.reservation.entities.Reservation;
import com.project.restaurant.reservation.repositories.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * This method is used to list all reservations
     *
     * @param pageDTO This is a page
     * @return list all of reservations
     */
    @Override
    public Page<Reservation> getAllReservation(PageDTO pageDTO) {
        return reservationRepository.findAll(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }

    /**
     * This method is used to save a reservation
     *
     * @param reservation This is a reservation
     * @return a reservation
     */
    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    /**
     * This method is used to get a reservation base on id
     *
     * @param id This is reservation id
     * @return reservation base on id
     */
    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to delete a reservation by id
     *
     * @param id This is reservation id
     */
    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    /**
     * This method is used to find reservation by keyword
     *
     * @param searchReservationDTO This is keyword
     * @return List of reservations
     */
    @Override
    public Page<Reservation> search(SearchReservationDTO searchReservationDTO) {
        return reservationRepository.searchReservation(
                searchReservationDTO.getKeyword(),
                searchReservationDTO.getBookingTime(),
                searchReservationDTO.getStatus(),
                PageRequest.of(searchReservationDTO.getPageDTO().getPageNumber(), searchReservationDTO.getPageDTO().getPageSize()));
    }
}
