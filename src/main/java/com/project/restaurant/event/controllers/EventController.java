package com.project.restaurant.event.controllers;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.dtos.ResponseDTO;
import com.project.restaurant.enums.Response;
import com.project.restaurant.event.dtos.EventDTO;
import com.project.restaurant.event.entities.Event;
import com.project.restaurant.event.services.EventService;
import com.project.restaurant.mapper.EventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Represents an event controller
 *
 * @author xuanmai
 * @since 2023-11-24
 */
@RestController
@RequestMapping("${EVENT}")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

    private final ResponseDTO body = ResponseDTO.getInstance();

    /**
     * Logging in Spring Boot
     */
    Logger logger = LoggerFactory.getLogger(EventController.class);

    /**
     * REST API methods for Retrieval operations
     *
     * @param pageDTO   This is a page
     * @param available This is available of events
     * @return list all of events
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<?> getAllEvents(@RequestBody PageDTO pageDTO, @RequestParam(required = false) Boolean available) {
        try {
            Page<EventDTO> eventPageDTO;
            if (available != null) {

                //get events by available
                eventPageDTO = eventService.getListEventByAvailable(available, pageDTO)
                        .map(event -> eventMapper.toEventDTO(event));
            } else {

                //get all events
                eventPageDTO = eventService.getAllEvents(pageDTO)
                        .map(event -> eventMapper.toEventDTO(event));
            }

            //Not empty
            if (!eventPageDTO.isEmpty()) {
                return new ResponseEntity<>(eventPageDTO, HttpStatus.OK);
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
     * Build create event REST API
     *
     * @param event This is an event
     * @return http status
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        try {
            // Check manufacture date <= expire date
            if (event.getManufactureDate().isBefore(event.getExpireDate())
                    || event.getManufactureDate().isEqual(event.getExpireDate())) {

                //Set status
                LocalDate currentDate = LocalDate.now();
                if (event.getManufactureDate().isEqual(currentDate)) {
                    event.setAvailable(true);
                } else {
                    event.setAvailable(false);
                }

                // Save
                eventService.save(event);

                // Successfully
                body.setResponse(Response.Key.STATUS, Response.Value.SUCCESSFULLY);
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            }

            // Invalid value manufacture and expire date
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
     * Build get event by id REST API
     *
     * @param id This is event id
     * @return a event
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        try {
            Event event = eventService.getEventById(id);

            //Found
            if (event != null) {
                EventDTO eventDTO = eventMapper.toEventDTO(event);
                // Successfully
                return new ResponseEntity<>(eventDTO, HttpStatus.OK);
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
     * Build update event REST API
     *
     * @param event This is event details
     * @param id    This is event id
     * @return event is updated
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateEvent(@RequestBody Event event, @PathVariable Long id) {
        try {
            Event updateEvent = eventService.getEventById(id);

            //Found
            if (updateEvent != null) {

                // Check manufacture date <= expire date
                if (event.getManufactureDate().isBefore(event.getExpireDate())
                        || event.getManufactureDate().isEqual(event.getExpireDate())) {

                    Event _event = updateEvent;
                    _event.setEventName(event.getEventName());
                    _event.setDescription(event.getDescription());
                    _event.setManufactureDate(event.getManufactureDate());
                    _event.setExpireDate(event.getExpireDate());
                    _event.setPercentageDiscount(event.getPercentageDiscount());

                    //Set status
                    LocalDate currentDate = LocalDate.now();
                    if (event.getManufactureDate().isEqual(currentDate)) {
                        _event.setAvailable(true);
                    } else {
                        _event.setAvailable(false);
                    }

                    //Successfully
                    return new ResponseEntity<>(eventService.save(_event), HttpStatus.OK);
                }

                // Invalid value manufacture and expire date
                body.setResponse(Response.Key.STATUS, Response.Value.INVALID_VALUE);
                return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
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
     * Build delete event REST API
     *
     * @param id This is event
     * @return http status
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        try {
            Event event = eventService.getEventById(id);

            //Found
            if (event != null) {
                //Delete
                eventService.delete(id);
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
}
