package Calendar.core.models;
import javax.persistence.*;
import java.util.Date;

@Entity(name="event")
@Table(name="event")
public class Event {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String title;
    private Date start;
    private Date end;
    private Date originalStart;
    private Boolean completed = false;
    @Column (name="class")
    private String className = "";
    private Integer repetition;
    private long userId;

    public Event(){

    }

    public Event(String title_, Date start_, Date end_){
        title = title_;
        start = start_;
        end = end_;
        originalStart = end_;

        // Don't forget to add current user ID here!
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

    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }
    public void setStart(Date start) {
        this.start = start;
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

    public Integer getRepetition() {
        return repetition;
    }

    public Date getOriginalStart() {
        return originalStart;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString(){
        return "id="+id+", title="+title+", start="+start + ", end=" + end;
    }
}
