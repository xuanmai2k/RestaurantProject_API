package com.project.restaurant.dtos;

import com.project.restaurant.enums.Response;

import java.util.LinkedHashMap;

/**
 * Custom response status
 *
 * @author xuanmai
 * @since 2023-11-22
 */
public class ResponseDTO extends LinkedHashMap<Response.Key, Object> {
    private static ResponseDTO instance;

    public static synchronized ResponseDTO getInstance() {
        if (instance == null) {
            instance = new ResponseDTO();
        }

        return instance;
    }

    public void setResponse(Response.Key key, Object value) {
        this.clear();
        this.put(key, value);
    }
}
