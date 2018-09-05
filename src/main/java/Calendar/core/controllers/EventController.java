package Calendar.core.controllers;


import Calendar.core.models.Event;
import Calendar.core.repositories.EventRepository;
import Calendar.core.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventService eventService;

    // Change to return current user events only!
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public @ResponseBody Iterable<Event> getEvents() {
        return eventService.findAllByUserId();
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public void createEvent(@RequestBody Event event) {
        eventService.save(event);
   }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable("id") long id)
    {
        eventService.deleteById(id);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Event getEvent(@PathVariable("id") long id) {
        return eventService.findById(id);
    }

    /*

    === javascript ===
    $.ajax({ url: '/events_ololo',
                method: 'POST',
                data: {
                  param1: 'value1',
                  param2: 'value2'
                }
          }).done(function() {
                 });


    === java ===

    @RequestMapping(value = "/events_ololo", method = RequestMethod.POST)
    public void eventOlolo(@RequestParam(value="param1") String myParam, @RequestParam(value="param2") String myParam2) {

    }

    */

    @RequestMapping(value = "/events", method = RequestMethod.PUT)
    public void saveEvent (@RequestBody Event event){
        eventService.save(event);
    }

    @RequestMapping(value = "/events/{id}/completed", method = RequestMethod.PUT)
    public void setEventCompleted(@PathVariable(value="id") long id) {
        eventService.setEventCompletedFlag(id);
    }

}


