package Calendar.core.services;

import Calendar.core.models.Event;
import Calendar.core.repositories.EventRepository;
import Calendar.exceptions.DataMismatchException;
import Calendar.exceptions.EntityNotFoundException;
import Calendar.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Autowired
    CustomUserDetailsService userService;

    @Transactional(readOnly = true)
    public Iterable<Event> findAll() {
        Iterable<Event> result = eventRepository.findAll();
        return result;
    }

    @Transactional(readOnly = true)
    public Iterable<Event> findAllByUserId() {
        long userId = userService.getCurrentUserId();
        Iterable<Event> result = eventRepository.findAllByUserId(userId);
        return result;
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
        if(eventRepository.findById(id).isPresent()) {
            eventRepository.deleteByEventId(id, userId);
        }
        else throw new DataMismatchException("Event with id = " + id + " does not exist");
    }

    @Transactional
    public void processEventCompleted(long id) {
        Event completedEvent = findByEventId(id);
        if (completedEvent.isRecurrent()) {
            save(copyEventWithShiftOnRecurrence(completedEvent));
        }
        eventRepository.setEventCompletedFlag(id);
    }


    private Event copyEventWithShiftOnRecurrence(Event copiedEvent) {
        Event event = new Event();
        event.setTitle(copiedEvent.getTitle());
        event.setStart(copiedEvent.getStart().plusDays(copiedEvent.getRecurrence()));
        event.setEnd(copiedEvent.getEnd().plusDays(copiedEvent.getRecurrence()));
        event.setRecurrence(copiedEvent.getRecurrence());
        return event;
    }

}
