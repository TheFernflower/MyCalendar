package com.github.thefernflower.calendar.core.models;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name="event")
@Table(name="event")
public class Event {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String title;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private ZonedDateTime originalStart;
    private Boolean completed = false;
    @Column (name = "class")
    private String className = "";
    @Nullable
    private Long recurrence;
    private long userId;

    public Event(){

    }

    public Event(String title, ZonedDateTime start, ZonedDateTime end){
        this.title = title;
        this.start = start;
        this.end = end;
        originalStart = start;
        //the current user ID is added in CustomUserDetailsService when an event is created
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public ZonedDateTime getEnd() {
        return end;
    }
    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public ZonedDateTime getStart() {
        return start;
    }
    public void setStart(ZonedDateTime start) {
        this.start = start;
        if (this.originalStart == null) {
            this.originalStart = start;
        }
    }

    public Boolean getCompleted()
    {
        return this.completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public Long getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Long recurrence) {
        this.recurrence = recurrence;
    }

    public ZonedDateTime getOriginalStart() {
        return originalStart;
    }

    public void setOriginalStart(ZonedDateTime originalStart) {
        this.originalStart = originalStart;
    }



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isRecurrent(){
        return recurrence != null;
    }

    @Override
    public String toString(){
        return "id="+id+", title="+title+", start="+start + ", end=" + end + ", class=" + className;
    }
}
