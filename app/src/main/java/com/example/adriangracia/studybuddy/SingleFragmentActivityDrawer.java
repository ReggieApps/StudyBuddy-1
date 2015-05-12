package com.example.adriangracia.studybuddy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.ListView;

import com.example.adriangracia.studybuddy.R;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Robby on 5/12/2015.
 */
public abstract class SingleFragmentActivityDrawer extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_activity_with_drawer);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        //button = (Button)findViewById(R.id.list_view_left_drawer);

        Fragment aFrag = getSupportFragmentManager().findFragmentById(R.id.frame_layout_drawer_content_frame);

        if(aFrag == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_drawer_content_frame, getFragment()).commit();
        }



        setUpDrawerToggles();
    }


    //Set up the menu bar icon eventually!
    private void setUpDrawerToggles(){
        //new ActionBarDrawerToggle(this,drawerLayout,android.R.drawable.dra)
    }


    public DrawerLayout getDrawerLayout(){
        return drawerLayout;
    }

    public Button getDrawerListView(){
        return button;
    }



    public abstract Fragment getFragment();
}