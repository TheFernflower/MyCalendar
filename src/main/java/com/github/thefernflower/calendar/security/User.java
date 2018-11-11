package com.github.thefernflower.calendar.security;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZoneId;

@Entity(name="user")
@Table(name="user")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private String password;

    @Column(name = "zone_id")
    private String zoneId;

    public User(){
        this.zoneId = "Europe/Oslo";
    }

    public User(String login, String password){
        this.login = login;
        this.password = password;
        this.zoneId = "Europe/Oslo";
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
     //   BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      //  this.password = passwordEncoder.encode(password);
        this.password = password;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public ZoneId getZoneIdObject(){
        return ZoneId.of(zoneId);
    }

    @Override
    public String toString(){
        return this.id + " " + this.login + " " + this.password;
    }


}
