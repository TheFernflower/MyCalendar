package Calendar.controllers;


import Calendar.models.Event;
import Calendar.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class EventController {

    @Autowired
            /*@Autowired - внедрение зависимости. В поле, помеченное этой аннотацией, фреймворк подставит компонент
            соответствующего типа. Если компонентов заданного типа несколько, нужно указать название необходимого
            компонента.*/
    EventRepository eventRepository;

    @RequestMapping(value = "/events/userId", method = RequestMethod.GET)
    public @ResponseBody Iterable<Event> getEvents(@PathVariable("id") long id) {
        return eventRepository.findAll(id);
    }



    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public void createEvent(@RequestBody Event event) {
        eventRepository.save(event);
   }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable("id") long id)
    {
        eventRepository.deleteById(id);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Event> getEvent(@PathVariable("id") long id) {
        return eventRepository.findById(id);
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
        eventRepository.save(event);
    }

    @RequestMapping(value = "/events/{id}/completed", method = RequestMethod.PUT)
    public void setEventCompleted(@PathVariable(value="id") long id) {
        eventRepository.setEventCompletedFlag(id);
    }

}


