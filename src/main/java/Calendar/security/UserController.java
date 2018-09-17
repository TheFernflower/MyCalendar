package Calendar.security;

import Calendar.core.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    long getCurrentUserId() {
        return customUserDetailsService.getCurrentUserId();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void createEvent(@RequestBody User user) {
        customUserDetailsService.save(user);
    }

}
