package com.example.adriangracia.studybuddy.objects;

import java.io.Serializable;

/**
 * Created by idk on 4/22/2015.
 */
public class EventObject implements Serializable{

    String title;
    String location;
    String description;
    TimeObject to;

    public EventObject(String title, String location, String description, TimeObject to){
        this.title=title;
        this.location=location;
        this.description=description;
        this.to=to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TimeObject getTo() {
        return to;
    }

    public void setTo(TimeObject to) {
        this.to = to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
