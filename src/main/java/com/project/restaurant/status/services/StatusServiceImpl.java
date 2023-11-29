package com.project.restaurant.status.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.status.entities.Status;
import com.project.restaurant.status.repositories.StatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService{
    @Autowired
    private StatusRepository statusRepository;

    /**
     * This method is used to list all status
     *
     * @param pageDTO This is a page
     * @return list all of status
     */
    @Override
    public Page<Status> listAll(PageDTO pageDTO) {
        return statusRepository.findAll(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }

    /**
     * This method is used to create a status
     *
     * @param status This is a status
     */
    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    /**
     * This method is used to get a status base on id
     *
     * @param id This is status id
     * @return status base on id
     */
    @Override
    public Optional<Status> getStatusById(Long id) {
        return statusRepository.findById(id);
    }

    /**
     * This method is used to delete a status by id
     *
     * @param id This is status id
     */
    @Override
    public void delete(Long id) {
        statusRepository.deleteById(id);
    }

    /**
     * This method is used to check status name existence
     *
     * @param statusName This is status name
     * @return boolean
     */
    @Override
    public boolean checkForExistence(String statusName) {
        return statusRepository.existsByStatusName(statusName);
    }

    /**
     * This method is used to find status by keyword
     *
     * @param keyword This is keyword
     * @return List of status
     */
    @Override
    public Page<Status> search(String keyword, PageDTO pageDTO) {
        return statusRepository.findByStatusNameContaining(keyword, PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }
}
