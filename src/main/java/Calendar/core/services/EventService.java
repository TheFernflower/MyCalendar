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
    public void setEventCompletedFlag(long id){
        eventRepository.setEventCompletedFlag(id);
    }



}
