package Calendar.repositories;

import Calendar.models.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface EventRepository extends CrudRepository<Event, Long>{

    @Modifying
    @Transactional
    @Query("update event e set e.completed = true, e.className = 'fc-event-completed' where e.id = ?1")
    int setEventCompletedFlag(long id);


    @Transactional
    @Query("select e from event e where e.user_id = ?1")
    Iterable<Event> findAll(long userId);

    @Transactional
    @Query("select e from event e where e.class <> 'fc-event-completed' ")
    Iterable<Event> findAllNonCompleted();
}
