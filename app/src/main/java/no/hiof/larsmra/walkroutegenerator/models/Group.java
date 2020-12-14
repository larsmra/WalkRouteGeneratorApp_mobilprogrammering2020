package no.hiof.larsmra.walkroutegenerator.models;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Group implements Serializable {

    @Exclude
    private String uid;
    private String name;
    private String description;
    private String creator;

    public Group() {}

    public Group(String name, String description, String creator) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Group{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
