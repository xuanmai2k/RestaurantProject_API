package com.project.restaurant.status.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.status.entities.Status;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StatusService {
    /**
     * This method is used to list all status
     *
     * @param pageDTO This is a page
     * @return list all of status
     */
    Page<Status> listAll(PageDTO pageDTO);

    /**
     * This method is used to create a status
     *
     * @param status This is a status
     */
    public Status save(Status status);

    /**
     * This method is used to get a category base on id
     *
     * @param id This is category id
     * @return category base on id
     */
    Optional<Status> getStatusById(Long id);

    /**
     * This method is used to delete a status by id
     *
     * @param id This is status id
     */
    void delete(Long id);

    /**
     * This method is used to check status name existence
     *
     * @param statusName This is status name
     * @return boolean
     */
    boolean checkForExistence(String statusName);

    /**
     * This method is used to find status by keyword
     *
     * @param keyword This is keyword
     * @return List of status
     */
    Page<Status> search(String keyword, PageDTO pageDTO);
}
