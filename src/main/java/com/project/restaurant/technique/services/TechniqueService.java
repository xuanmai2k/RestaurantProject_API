package com.project.restaurant.technique.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.technique.entities.Technique;
import org.springframework.data.domain.Page;

public interface TechniqueService {

    /**
     * This method is used to list all technique
     *
     * @param pageDTO This is a page
     * @return list all of technique
     */
    Page<Technique> getAllTechniques(PageDTO pageDTO);

    /**
     * This method is used to create a technique
     *
     * @param technique This is a technique
     */
    Technique save(Technique technique);

    /**
     * This method is used to get a technique base on id
     *
     * @param id This is technique id
     * @return technique base on id
     */
    Technique getTechniqueById(Long id);

    /**
     * This method is used to delete a technique by id
     *
     * @param id This is technique id
     */
    void delete(Long id);

    /**
     * This method is used to check technique name existence
     *
     * @param techniqueName This is technique name
     * @return boolean
     */
    boolean checkForExistence(String techniqueName);

    /**
     * This method is used to find technique by keyword
     *
     * @param keyword This is keyword
     * @return List of techniques
     */
    Page<Technique> search(String keyword, PageDTO pageDTO);
}
