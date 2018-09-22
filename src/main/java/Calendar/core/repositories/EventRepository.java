package Calendar.core.repositories;

import Calendar.core.models.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface EventRepository extends CrudRepository<Event, Long>{

    @Modifying
    @Transactional
    @Query("update event e set e.completed = true, e.className = 'fc-event-completed' where e.id = ?1")
    int setEventCompletedFlag(long id);


    @Transactional
    @Query("select e from event e where e.userId = ?1")
    Iterable<Event> findAllByUserId(long userId);

    @Transactional
    @Query("select e from event e where e.className <> 'fc-event-completed' ")
    Iterable<Event> findAllNonCompleted();

    @Transactional
    @Query("select e from event e where e.id = ?1 and e.userId = ?2")
    Optional<Event> findByEventId(long eventId, long userId);

    @Modifying
    @Transactional
    @Query("delete from event e where e.id = ?1 and e.userId = ?2")
    void deleteByEventId(long eventId, long userId);
}
