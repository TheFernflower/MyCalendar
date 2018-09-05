package Calendar.core.repositories;

import Calendar.core.models.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface TaskRepository extends CrudRepository<Task, Long> {

    @Transactional
    @Query("select t from task t where t.userId = ?1")
    Iterable<Task> findAllByUserId(long userId);
}
