package com.github.thefernflower.calendar.core.services;

import com.github.thefernflower.calendar.core.models.Event;
import com.github.thefernflower.calendar.core.repositories.EventRepository;
import com.github.thefernflower.calendar.exceptions.DataMismatchException;
import com.github.thefernflower.calendar.exceptions.EntityNotFoundException;
import com.github.thefernflower.calendar.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CustomUserDetailsService userService;

    @Transactional(readOnly = true)
    public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Iterable<Event> findAllByUserId() {
        long userId = userService.getCurrentUserId();
        ZoneId zoneId = userService.getCurrentUserZoneId();
        Iterable<Event> tzAdjustedEvents = eventRepository.findAllByUserId(userId);
        for (Event event : tzAdjustedEvents) {
            event.setStart(event.getStart().withZoneSameInstant(zoneId));
            event.setEnd(event.getEnd().withZoneSameInstant(zoneId));
            event.setOriginalStart(event.getOriginalStart().withZoneSameInstant(zoneId));
        }
        return tzAdjustedEvents;
    }

    @Transactional
    public void save(Event event){
        event.setUserId(userService.getCurrentUserId());
        eventRepository.save(event);
    }

    @Transactional
    public Event findByEventId(long id) {
        long userId = userService.getCurrentUserId();
        return eventRepository.findByEventId(id, userId)
                .orElseThrow(() -> new EntityNotFoundException("Event with id = " + id + " not found"));
    }

    @Transactional
    public void deleteByEventId(long id){
        long userId = userService.getCurrentUserId();
        if(findByEventId(id) != null) {
            eventRepository.deleteByEventId(id, userId);
        }
        else throw new DataMismatchException("Event with id = " + id + " does not exist");
    }

    @Transactional
    public void processEventCompleted(long id) {
        Event completedEvent = findByEventId(id);
        if (completedEvent.isRecurrent()) {
            save(buildNextRecurringEvent(completedEvent));
        }
        eventRepository.setEventCompletedFlag(id);
    }


    private Event buildNextRecurringEvent(Event sourceEvent) {
        Event event = new Event();
        event.setTitle(sourceEvent.getTitle());
        event.setStart(sourceEvent.getStart().plusDays(sourceEvent.getRecurrence()));
        event.setEnd(sourceEvent.getEnd().plusDays(sourceEvent.getRecurrence()));
        event.setRecurrence(sourceEvent.getRecurrence());
        return event;
    }

}
