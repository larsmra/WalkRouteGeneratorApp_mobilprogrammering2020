package no.hiof.larsmra.walkroutegenerator.models;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

public class Schedule implements Serializable {

    @Exclude
    private String uid;
    private String title;
    //private Date time;
    private String time;
    private boolean[] days;

    public Schedule(String title, /*Date*/ String time, boolean[] days) {
        this.title = title;
        this.time = time;
        this.days = days;
    }

    @Exclude
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
     */

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean[] getDays() {
        return days;
    }

    public void setDays(boolean[] days) {
        this.days = days;
    }
}
