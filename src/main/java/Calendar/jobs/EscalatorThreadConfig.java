package Calendar.jobs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by ferney on 09.09.2018.
 */
@Configuration
public class EscalatorThreadConfig {
    @Bean
    public TaskExecutor escalatorExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setThreadNamePrefix("event_escalator");
        executor.initialize();
        return executor;
    }
}
