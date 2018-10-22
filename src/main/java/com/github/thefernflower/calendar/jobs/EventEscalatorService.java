package com.github.thefernflower.calendar.jobs;


import com.github.thefernflower.calendar.core.models.Event;
import com.github.thefernflower.calendar.core.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class EventEscalatorService {

    @Autowired
    EventRepository eventRepository;

    public EventEscalatorService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void shiftEvent(){

        //if (eventRepository == null) System.out.println("eventRepository is null!");
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
    private ZonedDateTime moveTodayKeepingTime(ZonedDateTime dateTime){
       return ZonedDateTime.of(LocalDate.now(), dateTime.toLocalTime(), dateTime.getZone());
    }

    private boolean isAfter(ZonedDateTime dateTime){
        return ZonedDateTime.now().isAfter(dateTime);
    }

    /*private long countDifferenceInDays(ZonedDateTime dateTime){
        Period period = Period.between(dateTime.toLocalDate(), LocalDate.now());
        return period.getDays();
    }*/

    private long countDifferenceInDays(ZonedDateTime dateTime){
        return dateTime.until(ZonedDateTime.now(), ChronoUnit.DAYS);
    }

}
