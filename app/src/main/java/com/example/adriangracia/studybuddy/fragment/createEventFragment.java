package com.example.adriangracia.studybuddy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.dialogs.TimePickerDialogFragment;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class createEventFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CreateEventFragment";
    public static final int REQUEST_CODE = 1;

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.button_create_pick_time:
                TimePickerDialogFragment picker = new TimePickerDialogFragment();
                picker.setTargetFragment(createEventFragment.this,REQUEST_CODE);
                picker.show(getActivity().getSupportFragmentManager(),TAG);
                break;
            case R.id.button_create_pick_duration:
                break;
            default:
                break;
        }
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_event, container, false);



        Button pickTime = (Button)v.findViewById(R.id.button_create_pick_time);
        pickTime.setOnClickListener(this);

        Button chooseDuration = (Button)v.findViewById(R.id.button_create_pick_duration);
        chooseDuration.setOnClickListener(this);




        
        return v;
    }
}
