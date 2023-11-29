package com.project.restaurant.reservation.controllers;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.dtos.ResponseDTO;
import com.project.restaurant.enums.Response;
import com.project.restaurant.mapper.ReservationMapper;
import com.project.restaurant.reservation.dtos.ReservationDTO;
import com.project.restaurant.reservation.dtos.SearchReservationDTO;
import com.project.restaurant.reservation.entities.Reservation;
import com.project.restaurant.reservation.services.ReservationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Represents a reservation controller
 *
 * @author xuanmai
 * @since 2023-11-24
 */
@RestController
@RequestMapping("${RESERVATION}")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationMapper reservationMapper;

    /**
     * @value PENDING
     */
    @Value("${PENDING}")
    private String PENDING;

    private final ResponseDTO body = ResponseDTO.getInstance();

    /**
     * Logging in Spring Boot
     */
    Logger logger = LoggerFactory.getLogger(ReservationController.class);

    /**
     * REST API methods for Retrieval operations
     *
     * @param pageDTO This is a page
     * @return list all of reservations
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllReservation(@RequestBody PageDTO pageDTO) {
        try {
            Page<ReservationDTO> reservationPage = reservationService.getAllReservation(pageDTO)
                    .map(reservation -> reservationMapper.toReservationDTO(reservation));

            //Not empty
            if (!reservationPage.isEmpty()) {
                return new ResponseEntity<>(reservationPage, HttpStatus.OK);
            }

            // No content
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build create reservation REST API
     *
     * @param reservation This is a reservation
     * @return a reservation is inserted into database
     */
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
        try {
            LocalDateTime now = LocalDateTime.now();
            // Check Date
            if (reservation.getBookingTime().isEqual(now) || reservation.getBookingTime().isAfter(now)) {
                reservation.setStatus(PENDING);
                reservationService.save(reservation);

                // Successfully
                body.setResponse(Response.Key.STATUS, Response.Value.SUCCESSFULLY);
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            }

            // Invalid value bookingTime
            body.setResponse(Response.Key.STATUS, Response.Value.INVALID_VALUE);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Build get reservation by id REST API
     *
     * @param id This is reservation id
     * @return a reservation
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        try {
            Reservation reservation = reservationService.getReservationById(id);

            //Found
            if (reservation != null) {
                ReservationDTO reservationDTO = reservationMapper.toReservationDTO(reservation);
                // Successfully
                return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
            }

            // Not found
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build update reservation REST API
     *
     * @param reservation This is reservation details
     * @param id          This is reservation id
     * @return reservation is updated
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateReservation(@RequestBody @Valid Reservation reservation, @PathVariable Long id) {
        try {
            Reservation updateReservation = reservationService.getReservationById(id);
            LocalDateTime now = LocalDateTime.now();

            //Found
            if (updateReservation != null) {
                Reservation _reservation = updateReservation;

                //check BookingTime
                if (reservation.getBookingTime() != null) {
                    if (reservation.getBookingTime().isBefore(now)) {
                        // Invalid value bookingTime
                        body.setResponse(Response.Key.STATUS, Response.Value.INVALID_VALUE);
                        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
                    }
                    _reservation.setBookingTime(reservation.getBookingTime());
                }

                _reservation.setCustomerName(reservation.getCustomerName());
                _reservation.setCustomerPhone(reservation.getCustomerPhone());
                _reservation.setTableNumber(reservation.getTableNumber());
                _reservation.setDiners(reservation.getDiners());
                _reservation.setStatus(reservation.getStatus());

                //Successfully
                return new ResponseEntity<>(reservationService.save(_reservation), HttpStatus.OK);
            }

            // Not found
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build delete reservation REST API
     *
     * @param id This is reservation
     * @return http status
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        try {
            Reservation reservation = reservationService.getReservationById(id);

            //Found
            if (reservation != null) {

                //Found
                reservationService.delete(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //Not Found
            body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Build search reservation REST API
     *
     * @param searchReservationDTO This is keyword
     * @return http status
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/search")
    public ResponseEntity<?> searchReservation(@RequestBody SearchReservationDTO searchReservationDTO) {
        try {
            Page<ReservationDTO> reservationPage = reservationService.search(searchReservationDTO)
                    .map(reservation -> reservationMapper.toReservationDTO(reservation));

            //Not found
            if (reservationPage.isEmpty()) {
                //No content
                body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
                return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
            }

            //Found
            return new ResponseEntity<>(reservationPage, HttpStatus.OK);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            //Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
