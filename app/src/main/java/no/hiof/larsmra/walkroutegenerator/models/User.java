package no.hiof.larsmra.walkroutegenerator.models;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable, Comparable<User> {

    @Exclude
    private String uid;
    private String name;
    private String email;
    private long points;
    private List<String> schedules;
    private List<String> friends;
    private List<String> groups;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        points = 0;
        schedules = new ArrayList<>();
        friends = new ArrayList<>();
        groups = new ArrayList<>();
    }

    public User(String uid, String name, String email, long points,
                List<String> schedules, List<String> friends, List<String> groups) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.points = points;
        this.schedules = schedules;
        this.friends = friends;
        this.groups = groups;
    }

    @Exclude
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", points=" + points +
                ", schedules=" + schedules +
                ", friends=" + friends +
                ", groups=" + groups +
                '}';
    }

    @Override
    public int compareTo(User user) {
        return (int) user.points - (int) points;
    }
}
