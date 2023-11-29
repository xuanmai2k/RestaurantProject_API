package com.project.restaurant.event.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.event.entities.Event;
import com.project.restaurant.event.repositories.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents an event service
 *
 * @author xuanmai
 * @since 2023-11-24
 */
@Service
@Transactional
@Component
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    /**
     * This method is used to get list All events
     *
     * @param pageDTO This is a page
     * @return list all of events
     */
    @Override
    public Page<Event> getAllEvents(PageDTO pageDTO) {
        return eventRepository.findAll(PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }

    /**
     * This method is used to get list events follow by available
     *
     * @param available This is a status of event
     * @param pageDTO   This is a page
     * @return list all of events
     */
    @Override
    public Page<Event> getListEventByAvailable(Boolean available, PageDTO pageDTO) {
        return eventRepository.findByAvailable(available, PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize()));
    }

    /**
     * This method is used to get an event base on id
     *
     * @param id This is event id
     * @return event base on id
     */
    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to save an event
     *
     * @param event This is an event
     * @return an event
     */
    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    /**
     * This method is used to delete an event by id
     *
     * @param id This is event id
     */
    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    /**
     * Get all events which manufacturerDate is today to change the available
     */
    @Override
    public void updateAvailableEvent() {
        LocalDate currentDate = LocalDate.now();

        List<Event> events = eventRepository.findByManufactureDate(currentDate);

        for (Event event : events) {
            event.setAvailable(true);
            save(event);
        }
    }

    /**
     * Get all events which expireDate is today to change the available
     */
    @Override
    public void updateUnavailableEvent() {
        LocalDate currentDate = LocalDate.now();

        List<Event> events = eventRepository.findByExpireDate(currentDate);

        for (Event event : events) {
            event.setAvailable(false);
            save(event);
        }
    }
}
