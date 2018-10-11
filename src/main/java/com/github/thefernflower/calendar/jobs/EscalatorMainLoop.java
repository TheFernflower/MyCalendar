package com.github.thefernflower.calendar.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ferney on 06.09.2018.
 */

@Component
public class EscalatorMainLoop{

    //final long TIME_UPDATE = 3600000;
    final long TIME_UPDATE = 5000;

    @Autowired
    EventEscalatorService eventEscalatorService;

    @Scheduled(fixedDelay = TIME_UPDATE)
    public void checkIfEventDelay(){
        eventEscalatorService.shiftEvent();

    }

    }


