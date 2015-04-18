package com.example.adriangracia.studybuddy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AttendInformation extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attend_information);
//
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String[] value = extras.getStringArray("information");


            TextView timeAppend = (TextView)findViewById(R.id.time);
            TextView placeAppend = (TextView)findViewById(R.id.place);
            TextView sizeAppend = (TextView)findViewById(R.id.group_size);
            TextView peopleAttendingAppend = (TextView)findViewById(R.id.people_attending);
            TextView descriptionAppend = (TextView)findViewById(R.id.description);
            timeAppend.append("  " + value[0]);
            placeAppend.append("  " + value[1]);
            sizeAppend.append("  " + value[2]);
            peopleAttendingAppend.append("  " + value[3]);
            descriptionAppend.append("  " + value[4]);



        }

        Button finalizeAttend = (Button) findViewById(R.id.attend_button);
        finalizeAttend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Attend button pressed.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
