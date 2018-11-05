package com.github.thefernflower.calendar.security;

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


    @GetMapping("/users/current")
    long getCurrentUserId() {
        System.out.println(userRepository.findById(customUserDetailsService.getCurrentUserId()).get().getZoneIdObject());
        return customUserDetailsService.getCurrentUserId();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        customUserDetailsService.save(user);
    }

    @GetMapping("/users/{login}")
     public boolean doesLoginExist(@PathVariable("login") String login){
        return customUserDetailsService.doesLoginExist(login);
    }

    /*@GetMapping("/users/{password}")
    public void changePassword(@PathVariable("password") String password){
         customUserDetailsService.changePassword(password);
    }*/
}
