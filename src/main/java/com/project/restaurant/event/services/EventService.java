package com.project.restaurant.event.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.event.entities.Event;
import org.springframework.data.domain.Page;

/**
 * Represents an event service
 *
 * @author xuanmai
 * @since 2023-11-24
 */
public interface EventService {
    /**
     * This method is used to get all events
     *
     * @param pageDTO This is a page
     * @return list all of event
     */
    Page<Event> getAllEvents(PageDTO pageDTO);

    /**
     * This method is used to get list events by available
     *
     * @param available This is an available
     * @param pageDTO   This is a page
     * @return list of promotions by available
     */
    Page<Event> getListEventByAvailable(Boolean available, PageDTO pageDTO);

    /**
     * This method is used to get an event base on id
     *
     * @param id This is event id
     * @return event base on id
     */
    Event getEventById(Long id);

    /**
     * This method is used to save an event
     *
     * @param event This is an event
     */
    Event save(Event event);

    /**
     * This method is used to delete an event by id
     *
     * @param id This is event id
     */
    void delete(Long id);

    /**
     * This method is used to update available of event
     */
    void updateAvailableEvent();

    /**
     * This method is used to update unavailable of event
     */
    void updateUnavailableEvent();
}
