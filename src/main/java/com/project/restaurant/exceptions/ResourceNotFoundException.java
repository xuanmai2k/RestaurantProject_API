package com.project.restaurant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Represents an exception
 *
 * @author xuanmai
 * @since 2023-11-22
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    /**
     * Create a resource not found exception with the specified message
     *
     * @param msg This is message to show
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}