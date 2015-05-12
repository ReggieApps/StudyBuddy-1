package com.example.adriangracia.studybuddy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adriangracia.studybuddy.R;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class AttendInformationFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.attend_information, container, false);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            String[] value = extras.getStringArray("information");


            TextView timeAppend = (TextView)v.findViewById(R.id.editTime);
            TextView placeAppend = (TextView)v.findViewById(R.id.editPlace);
            TextView titleAppend = (TextView)v.findViewById(R.id.title);
            TextView durationAppend = (TextView)v.findViewById(R.id.editDuration);
            TextView descriptionAppend = (TextView)v.findViewById(R.id.editDescription);
            TextView subjectAppend = (TextView) v.findViewById(R.id.editSubject);
            timeAppend.append("  " + value[0]);
            placeAppend.append("  " + value[1]);
            titleAppend.append("  " + value[2]);
            durationAppend.append("  " + value[3]);
            descriptionAppend.append("  " + value[4]);
            subjectAppend.append(" " + value[5]);


        }

        Button finalizeAttend = (Button) v.findViewById(R.id.attend_button);
        finalizeAttend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Attend button pressed.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return v;
    }


}
