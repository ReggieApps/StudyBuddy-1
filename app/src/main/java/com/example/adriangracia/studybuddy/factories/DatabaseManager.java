package com.example.adriangracia.studybuddy.factories;

import android.content.Context;

/**
 * Singleton class to manage all server-side data communication
 * Created by jonathanmitchell on 4/23/15.
 */
public class DatabaseManager {


    private static DatabaseManager ourInstance;
    private Context context;

    public static DatabaseManager getInstance(Context context)
    {
        if(ourInstance==null){
            ourInstance = new DatabaseManager(context);
        }

        return ourInstance;
    }

    private DatabaseManager(Context c) {
        this.context=c;
    }
}
