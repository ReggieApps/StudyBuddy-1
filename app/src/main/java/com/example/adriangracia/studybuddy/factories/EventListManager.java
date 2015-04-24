package com.example.adriangracia.studybuddy.factories;

import android.content.Context;

/**
 * Created by jonathanmitchell on 4/22/15.
 */
public class EventListManager {



    private static EventListManager ourInstance;

    private Context context;

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
    }


}
