package com.example.adriangracia.studybuddy.objects;

import java.io.Serializable;

/**
 * Created by idk on 4/22/2015.
 */
public class EventObject implements Serializable{

    String title;
    String location;
    TimeObject to;

    public EventObject(String title, String location, TimeObject to){
        this.title=title;
        this.location=location;
        this.to=to;
    }

}
