package com.example.adriangracia.studybuddy.objects;

import java.io.Serializable;

/**
 * Created by idk on 4/22/2015.
 */
public class EventObject implements Serializable{

    private String title;
    private String location;
    private TimeObject to;

    public EventObject(String title, String location, TimeObject to){
        this.title=title;
        this.location=location;
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


}
