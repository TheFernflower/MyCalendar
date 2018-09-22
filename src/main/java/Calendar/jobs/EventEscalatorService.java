package Calendar.jobs;


import Calendar.core.models.Event;
import Calendar.core.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
public class EventEscalatorService {


    private EventRepository eventRepository = null;

    public EventEscalatorService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void shiftEvent(){
        System.out.println("inside");
        if (eventRepository == null) System.out.println("eventRepository is null!");
        List<Event> nonCompleted = (List<Event>) eventRepository.findAllNonCompleted();
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
    public LocalDateTime moveTodayKeepingTime(LocalDateTime dateTime){
       return LocalDateTime.of(LocalDate.now(), dateTime.toLocalTime());
    }

    public boolean isAfter(LocalDateTime dateTime){
        return LocalDate.now().isAfter(dateTime.toLocalDate());
    }

    public long countDifferenceInDays(LocalDateTime dateTime){
        Period period = Period.between(dateTime.toLocalDate(), LocalDate.now());
        return period.getDays();
    }

}
