package Calendar;

import Calendar.jobs.EscalatorLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        context.getBean(EscalatorLauncher.class).launchEscalatorMainLoop();

        /*TaskExecutor escalatorExecutor = context.getBean(EscalatorThreadConfig.class).escalatorExecutor();
        EscalatorMainLoop escalatorMainLoop = context.getBean(EscalatorMainLoop.class);
        escalatorExecutor.execute(escalatorMainLoop);*/

    }
}
