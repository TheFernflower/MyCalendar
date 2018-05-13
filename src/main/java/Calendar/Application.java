package Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

       /* ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        EventDAO eventDAO = context.getBean(EventDAO.class);

        Event event = new Event();
        event.setTitle("Урок английского"); event.setStart(new Date());

        eventDAO.save(event);

        System.out.println("Title:"+event);

        List<Event> list = eventDAO.list();

        for(Event e : list){
            System.out.println("Person List: " + e);
        }
        //close resources
        context.close();*/
    }
}
