package com.example.adriangracia.studybuddy;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.example.adriangracia.studybuddy.fragment.mainActivityFragment;


public class MainActivity extends singleFragmentActivity {
    //happy holidays!!
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        //Add a comment

//
        //Second comment
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Fragment getFragment() {
        return new mainActivityFragment();
    }

}
