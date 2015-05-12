package com.example.adriangracia.studybuddy.activities;

import android.support.v4.app.Fragment;

import com.example.adriangracia.studybuddy.fragment.CreateEventFragment;
import com.example.adriangracia.studybuddy.SingleFragmentActivity;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class CreateEvent extends SingleFragmentActivity {

    @Override
    public Fragment getFragment() {
        return new CreateEventFragment();
    }
}
