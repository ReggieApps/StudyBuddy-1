package com.example.adriangracia.studybuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.example.adriangracia.studybuddy.fragment.createEventFragment;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class createEvent extends singleFragmentActivity {

    @Override
    public Fragment getFragment() {
        return new createEventFragment();
    }
}
