package com.project.restaurant.status.controllers;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.dtos.ResponseDTO;
import com.project.restaurant.enums.Response;
import com.project.restaurant.status.entities.Status;
import com.project.restaurant.status.services.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Represents a status controller
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@RestController
@RequestMapping("${STATUS}")
public class StatusController {
    @Autowired
    private StatusService statusService;

    private final ResponseDTO body = ResponseDTO.getInstance();

    /**
     * Logging in Spring Boot
     */
    Logger logger = LoggerFactory.getLogger(StatusController.class);

    /**
     * REST API methods for Retrieval operations
     *
     * @param pageDTO This is a page
     * @return list all of status
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<?> getAllStatus(@RequestBody PageDTO pageDTO) {
        try {
            Page<Status> statusPage = statusService.listAll(pageDTO);

            //Not empty
            if (!statusPage.isEmpty()) {
                return new ResponseEntity<>(statusPage, HttpStatus.OK);
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
     * Build create category REST API
     *
     * @param status This is a status
     * @return a status is inserted into database
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createStatus(@RequestBody Status status) {
        try {

            if (statusService.checkForExistence(status.getStatusName()) == false) {
                statusService.save(status);

                // Successfully
                body.setResponse(Response.Key.STATUS, Response.Value.SUCCESSFULLY);
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            }

            // Duplicate manufacturerName
            body.setResponse(Response.Key.STATUS, Response.Value.DUPLICATED);
            return new ResponseEntity<>(body, HttpStatus.CONFLICT);
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            // Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Build get status by id REST API
     *
     * @param id This is status id
     * @return a status
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<?> getStatusById(@PathVariable long id) {
        try {
            Optional<Status> status = statusService.getStatusById(id);

            // Found
            if (status.isPresent()) {
                // Successfully
                return new ResponseEntity<>(status.get(), HttpStatus.OK);
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
     * Build update status REST API
     *
     * @param id     This is status id
     * @param status This status details
     * @return status is updated
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateStatus(@PathVariable long id, @RequestBody Status status) {
        try {
            Optional<Status> updateStatus = statusService.getStatusById(id);

            // Found
            if (updateStatus.isPresent()) {

                // Update
                Status _status = updateStatus.get();
                _status.setStatusName(status.getStatusName());

                // Successful
                return new ResponseEntity<>(statusService.save(_status), HttpStatus.OK);
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
     * Build delete status REST API
     *
     * @param id This is status
     * @return http status
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable long id) {
        try {
            Optional<Status> status = statusService.getStatusById(id);

            // Found
            if (status.isPresent()) {

                // Delete
                statusService.delete(id);

                //Successful
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
     * Build search status REST API
     *
     * @param keyword This is keyword
     * @return http status
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/search")
    public ResponseEntity<?> searchStatus(@RequestBody PageDTO pageDTO, @RequestParam String keyword) {
        try {
            Page<Status> statusPage = statusService.search(keyword, pageDTO);

            //Not found
            if (statusPage.isEmpty()) {
                //No content
                body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
                return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
            }

            //Found
            return new ResponseEntity<>(statusPage, HttpStatus.OK);

        } catch (Exception ex) {
            logger.info(ex.getMessage());

            //Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
