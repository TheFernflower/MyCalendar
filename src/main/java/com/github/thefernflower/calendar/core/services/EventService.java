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
        return updateTimeZones(tzAdjustedEvents, zoneId);
    }

    @Transactional
    public void save(Event event){
        event.setUserId(userService.getCurrentUserId());
        eventRepository.save(event);
    }

    @Transactional
    public Event findByEventId(long id) {
        long userId = userService.getCurrentUserId();
        ZoneId zoneId = userService.getCurrentUserZoneId();
        Event event = eventRepository.findByEventId(id, userId)
                .orElseThrow(() -> new EntityNotFoundException("Event with id = " + id + " not found"));
        updateTimeZone(event, zoneId);
        return event;
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

    private Iterable<Event> updateTimeZones(Iterable<Event> events, ZoneId zoneId){
        for (Event event : events) {
            updateTimeZone(event, zoneId);
        }
        return events;
    }

    private void updateTimeZone(Event event, ZoneId zoneId){
        event.setStart(event.getStart().withZoneSameInstant(zoneId));
        event.setEnd(event.getEnd().withZoneSameInstant(zoneId));
        event.setOriginalStart(event.getOriginalStart().withZoneSameInstant(zoneId));
    }
}
