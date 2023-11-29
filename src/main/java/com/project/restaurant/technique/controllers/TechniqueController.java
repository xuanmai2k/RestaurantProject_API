package com.project.restaurant.technique.controllers;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.dtos.ResponseDTO;
import com.project.restaurant.enums.Response;
import com.project.restaurant.mapper.TechniqueMapper;
import com.project.restaurant.technique.dtos.TechniqueDTO;
import com.project.restaurant.technique.entities.Technique;
import com.project.restaurant.technique.services.TechniqueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Represents a technique controller
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@RestController
@RequestMapping("${TECHNIQUE}")
public class TechniqueController {
    @Autowired
    private TechniqueService techniqueService;

    @Autowired
    private TechniqueMapper techniqueMapper;

    private final ResponseDTO body = ResponseDTO.getInstance();

    /**
     * Logging in Spring Boot
     */
    Logger logger = LoggerFactory.getLogger(TechniqueController.class);

    /**
     * REST API methods for Retrieval operations
     *
     * @param pageDTO This is a page
     * @return list all of techniques
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<?> getAllTechnique(@RequestBody PageDTO pageDTO) {
        try {
            Page<TechniqueDTO> techniquePage = techniqueService.getAllTechniques(pageDTO)
                    .map(technique -> techniqueMapper.toTechniqueDTO(technique));

            //Not empty
            if (!techniquePage.isEmpty()) {
                return new ResponseEntity<>(techniquePage, HttpStatus.OK);
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
     * Build create technique REST API
     *
     * @param technique This is a technique
     * @return a technique is inserted into database
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createTechnique(@RequestBody Technique technique) {
        try {

            //check existence
            if (techniqueService.checkForExistence(technique.getTechniqueName()) == false) {
                techniqueService.save(technique);

                // Successfully
                body.setResponse(Response.Key.STATUS, Response.Value.SUCCESSFULLY);
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            }

            // Duplicate techniqueName
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
     * Build get technique by id REST API
     *
     * @param id This is technique id
     * @return a technique
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<?> getTechniqueById(@PathVariable long id) {
        try {
            Technique technique = techniqueService.getTechniqueById(id);

            // Found
            if (technique != null) {
                // Successfully
                TechniqueDTO techniqueDTO = techniqueMapper.toTechniqueDTO(technique);
                return new ResponseEntity<>(techniqueDTO, HttpStatus.OK);
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
     * Build update technique REST API
     *
     * @param id        This is technique id
     * @param technique This technique details
     * @return technique is updated
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateTechnique(@PathVariable long id, @RequestBody Technique technique) {
        try {
            Technique updateTechnique = techniqueService.getTechniqueById(id);

            // Found
            if (updateTechnique != null) {

                //check existence
                if (techniqueService.checkForExistence(technique.getTechniqueName()) == false) {
                    // Update
                    Technique _technique = updateTechnique;
                    _technique.setTechniqueName(technique.getTechniqueName());

                    // Successfully
                    return new ResponseEntity<>(techniqueService.save(_technique), HttpStatus.OK);
                }

                // Duplicate techniqueName
                body.setResponse(Response.Key.STATUS, Response.Value.DUPLICATED);
                return new ResponseEntity<>(body, HttpStatus.CONFLICT);
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
     * Build delete technique REST API
     *
     * @param id This is technique
     * @return http status
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTechnique(@PathVariable long id) {
        try {
            Technique technique = techniqueService.getTechniqueById(id);

            // Found
            if (technique != null) {

                // Delete
                techniqueService.delete(id);

                //Successfully
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
     * Build search technique REST API
     *
     * @param keyword This is keyword
     * @return http status
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/search")
    public ResponseEntity<?> searchTechnique(@RequestBody PageDTO pageDTO, @RequestParam String keyword) {
        try {
            Page<TechniqueDTO> techniquePage = techniqueService.search(keyword, pageDTO)
                    .map(technique -> techniqueMapper.toTechniqueDTO(technique));

            //Not found
            if (techniquePage.isEmpty()) {
                //No content
                body.setResponse(Response.Key.STATUS, Response.Value.NOT_FOUND);
                return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
            }

            //Found
            return new ResponseEntity<>(techniquePage, HttpStatus.OK);

        } catch (Exception ex) {
            logger.info(ex.getMessage());

            //Failed
            body.setResponse(Response.Key.STATUS, Response.Value.FAILURE);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
