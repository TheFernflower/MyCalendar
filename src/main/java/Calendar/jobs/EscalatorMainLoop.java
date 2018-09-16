package Calendar.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ferney on 06.09.2018.
 */

@Component
public class EscalatorMainLoop implements Runnable {

    //final long TIME_UPDATE = 3600000;
    final long TIME_UPDATE = 5000;

    @Autowired
    EventEscalatorService eventEscalatorService;

    public void run() {

        System.out.println("In da thread " + Thread.currentThread().getName());

        while (eventEscalatorService == null) {
            System.out.println("eventEscalatorService is null!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (true) {
            try {
                eventEscalatorService.shiftEvent();
                Thread.sleep(TIME_UPDATE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    }


