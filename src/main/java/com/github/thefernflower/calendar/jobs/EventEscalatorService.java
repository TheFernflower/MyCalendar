package com.github.thefernflower.calendar.jobs;


import com.github.thefernflower.calendar.core.models.Event;
import com.github.thefernflower.calendar.core.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Service
public class EventEscalatorService {


    private EventRepository eventRepository;

    public EventEscalatorService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void shiftEvent(){

        if (eventRepository == null) System.out.println("eventRepository is null!");
        Iterable<Event> nonCompleted = eventRepository.findAllNonCompleted();
        for (Event event : nonCompleted) {
            if (isAfter(event.getStart())) {
                event.setStart(moveTodayKeepingTime(event.getStart()));
                event.setEnd(moveTodayKeepingTime(event.getEnd()));
                if(countDifferenceInDays(event.getOriginalStart()) == 1){
                    event.setClassName("fc-event-delayed");
                }
                else if(countDifferenceInDays(event.getOriginalStart())>1){
                    event.setClassName("fc-event-overdue");
                }
                eventRepository.save(event);
            }
         }
    }

    //set the current date and keep the time of event
    private LocalDateTime moveTodayKeepingTime(LocalDateTime dateTime){
       return LocalDateTime.of(LocalDate.now(), dateTime.toLocalTime());
    }

    private boolean isAfter(LocalDateTime dateTime){
        return LocalDate.now().isAfter(dateTime.toLocalDate());
    }

    private long countDifferenceInDays(LocalDateTime dateTime){
        Period period = Period.between(dateTime.toLocalDate(), LocalDate.now());
        return period.getDays();
    }

}
