package com.github.thefernflower.calendar.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ferney on 09.09.2018.
 */
@Component
public class EscalatorLauncher {

    @Autowired
    EscalatorMainLoop escalatorMainLoop;

    @Autowired
    EscalatorThreadConfig escalatorThreadConfig;

    public void launchEscalatorMainLoop() {
        escalatorThreadConfig.escalatorExecutor().execute((Runnable) escalatorMainLoop);
    }

}
