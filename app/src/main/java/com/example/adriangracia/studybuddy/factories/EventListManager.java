package com.example.adriangracia.studybuddy.factories;

import android.content.Context;

import com.example.adriangracia.studybuddy.objects.EventObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Singleton class which acts as a local database to store incoming information
 * retrieved from the remote database
 *
 * Created by jonathanmitchell on 4/22/15.
 */
public class EventListManager {

    private static EventListManager ourInstance;

    private Context context;

    //reference a list of event BY it's string department
    private HashMap<String,ArrayList<EventObject>> events;

    public static EventListManager getInstance(Context context)
    {
        if(ourInstance==null){
            ourInstance=new EventListManager(context);
            return ourInstance;
        }
        return ourInstance;
    }

    private EventListManager(Context c) {
        this.context=c;
        events = new HashMap<>();
    }

    public boolean addEvent(String department, EventObject event){

        //QUERY SERVER HERE to add to server first

        //...

        //RETURN FALSE, and kill the code here if it didn't update the server correctly

        //Otherwise, add the data to the app for the user to see

        ArrayList<EventObject> temp = events.get(department);

        //checks to see if a value exists at that key
        if(temp == null){
            //if it doesn't, make a new array list and put that array list in the map
            temp = new ArrayList<>();
            temp.add(event);
            events.put(department,temp);

        } else {
            //otherwise, just add it to the list in the map (since it already exists)
            events.get(department).add(event);
        }

        return true;
    }

    //There should eventually be a null checker if the department doesn't exist
    public ArrayList<EventObject> getEventsInDepartment(String department){
        ArrayList<EventObject> temp = events.get(department);


        //eventually, we want to sort the array list before we return it


        return temp;

    }




}
