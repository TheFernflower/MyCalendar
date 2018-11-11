package com.github.thefernflower.calendar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;

@Service
public class CustomUserDetailsService implements UserDetailsService {



    @Autowired
    UserRepository userRepository;


    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        /*if (user == null) {
            throw new EntityNotFoundException("User " + login + " not found");
        }*/
        //Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new CustomUserPrincipal(user);

    }

    @Transactional
    public long getCurrentUserId() {
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserPrincipal.getUserId();
    }

    @Transactional
    public ZoneId getCurrentUserZoneId() {
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserPrincipal.getZoneId();
    }

    @Transactional
    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean doesLoginExist(String login){
        return userRepository.findByLogin(login) != null;
    }

    @Transactional
    public void changePassword(String password){

    }

}
