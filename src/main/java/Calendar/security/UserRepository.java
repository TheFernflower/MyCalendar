package Calendar.security;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends CrudRepository<User, Long> {
    @Transactional(readOnly = true)
    @Query("SELECT u FROM user u WHERE login = ?1")
        //@Query(value = "SELECT * FROM main.user WHERE login = ?1", nativeQuery = true)
    User findByLogin(String login);
}
