package com.example.adriangracia.studybuddy.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adriangracia.studybuddy.MainActivity;
import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.dialogs.ChooseDurationDialogFragment;
import com.example.adriangracia.studybuddy.dialogs.TimePickerDialogFragment;
import com.example.adriangracia.studybuddy.objects.TimeObject;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class createEventFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CreateEventFragment";

    public static final int TIME_REQUEST_CODE = 1;
    public static final int DURATION_REQUEST_CODE = 2;


    private static final String EXTRA_TIME_OBJECT = "EXTRA_TIME_OBJ_";
    private static final String EXTRA_DURATION = "_EXTRA_DUR_";
    private static final String EXTRA_DESCRIPTION = "_EXTRA_DESCRIPT_";
    private static final String EXTRA_TITLE = "_EXTRA_TITLE_";
    private static final String EXTRA_PLACE = "_EXTRA_PLACE_";


    private Button pickTime;
    private Button chooseDuration;
    private Button createEvent;

    private EditText title;
    private EditText place;
    private EditText description;

    private int duration = -1;

    private TimeObject to;

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.button_create_pick_time:
                TimePickerDialogFragment picker = new TimePickerDialogFragment();
                picker.setTargetFragment(createEventFragment.this,TIME_REQUEST_CODE);
                picker.show(getActivity().getSupportFragmentManager(),TAG);
                break;
            case R.id.button_create_pick_duration:
                ChooseDurationDialogFragment dur = new ChooseDurationDialogFragment();
                dur.setTargetFragment(createEventFragment.this,DURATION_REQUEST_CODE);
                dur.show(getActivity().getSupportFragmentManager(),TAG);
                break;
            case R.id.create_event_finalize:
                if(title.getText().length()==0) Toast.makeText(getActivity(),"Please enter a title.", Toast.LENGTH_LONG).show();
                else if(place.getText().length()==0) Toast.makeText(getActivity(),"Please specify a location.", Toast.LENGTH_LONG).show();
                else if(to==null) Toast.makeText(getActivity(),"Please specify a time.", Toast.LENGTH_LONG).show();
                else{
                    Intent i = new Intent(getActivity(), MainActivity.class);

                    Bundle bun = new Bundle();
                    bun.putString("TITLE", title.getText().toString());
                    bun.putString("PLACE", place.getText().toString());
                    bun.putString("TIME", to.toString());

                    i.putExtra("EVENT_BUNDLE", bun);
                    startActivity(i);

                }
                break;
            default:
                break;
        }
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_event, container, false);

        pickTime = (Button)v.findViewById(R.id.button_create_pick_time);
        pickTime.setOnClickListener(this);

        chooseDuration = (Button)v.findViewById(R.id.button_create_pick_duration);
        chooseDuration.setOnClickListener(this);

        createEvent = (Button) v.findViewById(R.id.create_event_finalize);
        createEvent.setOnClickListener(this);

        title = (EditText)v.findViewById(R.id.edit_text_create_event_title);

        place = (EditText)v.findViewById(R.id.edit_text_create_event_place);

        description = (EditText)v.findViewById(R.id.edit_text_create_event_description);

        dealWithSavedState(savedInstanceState);


        return v;
    }

    private void dealWithSavedState(Bundle savedInstanceState){

        if(savedInstanceState!=null){
            to = (TimeObject)savedInstanceState.getSerializable(EXTRA_TIME_OBJECT);

            if(pickTime!=null && to!=null)
                pickTime.setText(to.toString());

            duration = savedInstanceState.getInt(EXTRA_DURATION,-1);

            if(duration!=-1 && chooseDuration!=null){
                chooseDuration.setText(duration+" hours");
            }

            String descriptionString = savedInstanceState.getString(EXTRA_DESCRIPTION);
            if(descriptionString!=null && !descriptionString.isEmpty() && description!=null){
                description.setText(descriptionString);
            }

            String titleString = savedInstanceState.getString(EXTRA_TITLE);
            if(titleString!=null && !titleString.isEmpty() && title!=null){
                title.setText(titleString);
            }

            String placeString = savedInstanceState.getString(EXTRA_PLACE);
            if(placeString!=null && !placeString.isEmpty() && place!=null){
                place.setText(placeString);
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK){
            Toast.makeText(getActivity(),"Error setting time. Please try later.",Toast.LENGTH_LONG).show();
            Log.e(TAG,"Bad Result Code");
            return;
        }

        switch(requestCode){
            case TIME_REQUEST_CODE:
                TimeObject to = (TimeObject)data.getSerializableExtra(TimePickerDialogFragment.TIME_OBJ);
                if(to==null){
                    Toast.makeText(getActivity(),"Error setting time. Please try later.",Toast.LENGTH_LONG).show();
                    Log.e(TAG,"Null TO");
                    return;
                }

                this.to = to;

                if(pickTime!=null){
                    pickTime.setText(to.toString());
                } else {
                    Log.e(TAG,"Button Null");
                }


                break;

            case DURATION_REQUEST_CODE:

                if(!data.hasExtra(ChooseDurationDialogFragment.EXTRA_DURATION)){
                    Toast.makeText(getActivity(),"Error, try again later",Toast.LENGTH_LONG).show();
                    return;
                }

                duration = data.getIntExtra(ChooseDurationDialogFragment.EXTRA_DURATION,-1);

                if(chooseDuration!=null){
                    chooseDuration.setText(duration+" hours");
                }

                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_TIME_OBJECT,to);
        outState.putInt(EXTRA_DURATION,duration);

        if(description!=null){
            outState.putString(EXTRA_DESCRIPTION,description.getText().toString());
        }

        if(title!=null){
            outState.putString(EXTRA_TITLE,title.getText().toString());
        }

        if(place!=null){
            outState.putString(EXTRA_PLACE,place.getText().toString());
        }

    }
}
