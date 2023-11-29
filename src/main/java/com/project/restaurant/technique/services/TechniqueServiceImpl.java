package com.project.restaurant.technique.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.technique.entities.Technique;
import com.project.restaurant.technique.repositories.TechniqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TechniqueServiceImpl implements TechniqueService {
    @Autowired
    private TechniqueRepository techniqueRepository;

    /**
     * This method is used to list all techniques
     *
     * @param pageDTO This is a page
     * @return list all of techniques
     */
    @Override
    public Page<Technique> getAllTechniques(PageDTO pageDTO) {
        return techniqueRepository.findAll(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }

    /**
     * This method is used to save a technique
     *
     * @param technique This is a technique
     * @return a technique
     */
    @Override
    public Technique save(Technique technique) {
        return techniqueRepository.save(technique);
    }

    /**
     * This method is used to get a technique base on id
     *
     * @param id This is technique id
     * @return technique base on id
     */
    @Override
    public Technique getTechniqueById(Long id) {
        return techniqueRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to delete a technique by id
     *
     * @param id This is technique id
     */
    @Override
    public void delete(Long id) {
        techniqueRepository.deleteById(id);
    }

    /**
     * This method is used to check technique name existence
     *
     * @param techniqueName This is technique name
     * @return boolean
     */
    @Override
    public boolean checkForExistence(String techniqueName) {
        return techniqueRepository.existsByTechniqueName(techniqueName);
    }

    /**
     * This method is used to find technique by keyword
     *
     * @param keyword This is keyword
     * @return List of techniques
     */
    @Override
    public Page<Technique> search(String keyword, PageDTO pageDTO) {
        return techniqueRepository.findByTechniqueNameContaining(keyword, PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }
}
