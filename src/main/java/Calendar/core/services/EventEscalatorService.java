package Calendar.core.services;


import Calendar.core.models.Event;
import Calendar.core.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EventEscalatorService {

    @Autowired
    EventRepository eventRepository;

    public void shiftEvent(){
        List<Event> nonCompleted = (List<Event>) eventRepository.findAllNonCompleted();
        for (Event event : nonCompleted) {
            if (isBefore(event.getStart())) {
                event.setStart(timeUtility(event.getStart()));
                event.setEnd(timeUtility(event.getEnd()));
                if(countDifferenceInDays(event.getStart()) == 1){
                    event.setClassName("fc-event-delayed");
                }
                else if(countDifferenceInDays(event.getStart())>1){
                    event.setClassName("fc-event-overdue");
                }
            }
            eventRepository.save(event);
        }
    }

    public Date timeUtility(Date date){
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(date);
        Calendar now = Calendar.getInstance();
        calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, now.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public boolean isBefore(Date date){
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(date);
        Calendar now = Calendar.getInstance();
        return calendar.before(now);
    }

    public long countDifferenceInDays(Date date){
        Date now = new Date();
        return now.getTime() - date.getTime();
    }


}
