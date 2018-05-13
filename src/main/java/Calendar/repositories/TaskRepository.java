package Calendar.repositories;

import Calendar.models.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ferney on 23.04.2018.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
}
