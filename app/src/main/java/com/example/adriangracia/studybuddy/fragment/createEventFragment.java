package com.example.adriangracia.studybuddy.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adriangracia.studybuddy.activities.MainActivity;
import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.dialogs.ChooseDurationDialogFragment;
import com.example.adriangracia.studybuddy.dialogs.TimePickerDialogFragment;
import com.example.adriangracia.studybuddy.factories.JSONParser;
import com.example.adriangracia.studybuddy.objects.TimeObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class CreateEventFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CreateEventFragment";

    public static final int TIME_REQUEST_CODE = 1;
    public static final int DURATION_REQUEST_CODE = 2;

    private static final String EXTRA_TIME_OBJECT = "EXTRA_TIME_OBJ_";
    private static final String EXTRA_DURATION = "_EXTRA_DUR_";
    private static final String EXTRA_DESCRIPTION = "_EXTRA_DESCRIP_";
    private static final String EXTRA_TITLE = "_EXTRA_TITLE_";
    private static final String EXTRA_PLACE = "_EXTRA_PLACE_";

    private Button pickTime;
    private Button chooseDuration;
    private Button createEvent;

    private Spinner subjectSpinner;

    private EditText eTitle;
    private EditText place;
    private EditText description;

    private int duration = -1;

    private TimeObject to;

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    // url to create new product
    private static String url_new_event = "http://isumobileclub.ilstu.edu/new_event.php";

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.button_create_pick_time:
                TimePickerDialogFragment picker = new TimePickerDialogFragment();
                picker.setTargetFragment(CreateEventFragment.this,TIME_REQUEST_CODE);
                picker.show(getActivity().getSupportFragmentManager(),TAG);
                break;
            case R.id.button_create_pick_duration:
                ChooseDurationDialogFragment dur = new ChooseDurationDialogFragment();
                dur.setTargetFragment(CreateEventFragment.this,DURATION_REQUEST_CODE);
                dur.show(getActivity().getSupportFragmentManager(),TAG);
                break;
            case R.id.create_event_finalize:
                if(eTitle.getText().length()==0) Toast.makeText(getActivity(),"Please enter a title.", Toast.LENGTH_LONG).show();
                else if(place.getText().length()==0) Toast.makeText(getActivity(),"Please specify a location.", Toast.LENGTH_LONG).show();
                else if(to==null) Toast.makeText(getActivity(),"Please specify a time.", Toast.LENGTH_LONG).show();
                else if(duration==-1) Toast.makeText(getActivity(),"Please specify a duration.", Toast.LENGTH_LONG).show();
                else{
                    new CreateNewProduct().execute();
                }
                break;
            default:
                break;
        }
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_event, container, false);

        eTitle = (EditText)v.findViewById(R.id.edit_text_create_event_title);

        place = (EditText)v.findViewById(R.id.edit_text_create_event_place);

        description = (EditText)v.findViewById(R.id.edit_text_create_event_description);

        pickTime = (Button)v.findViewById(R.id.button_create_pick_time);
        pickTime.setOnClickListener(this);

        chooseDuration = (Button)v.findViewById(R.id.button_create_pick_duration);
        chooseDuration.setOnClickListener(this);

        createEvent = (Button) v.findViewById(R.id.create_event_finalize);
        createEvent.setOnClickListener(this);

        subjectSpinner = (Spinner) v.findViewById(R.id.spinner_2);

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
            if(titleString!=null && !titleString.isEmpty() && eTitle!=null){
                eTitle.setText(titleString);
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

        if(eTitle!=null){
            outState.putString(EXTRA_TITLE,eTitle.getText().toString());
        }

        if(place!=null){
            outState.putString(EXTRA_PLACE,place.getText().toString());
        }
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Creating Event...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String title = eTitle.getText().toString();
            String location = place.getText().toString();
            String desc = description.getText().toString();
            String subj = subjectSpinner.getSelectedItem().toString();
            String time = to.toString();


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("title", title));
            params.add(new BasicNameValuePair("location", location));
            params.add(new BasicNameValuePair("description", desc));
            params.add(new BasicNameValuePair("subject", subj));
            params.add(new BasicNameValuePair("time", time));


            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_new_event,
                    "POST", params);


            // check log cat from response
            Log.d("Create Response", json.toString());

            // check for success tag
           // try {
                boolean success = json.toString().contains("1");

                if (success) {
                    // successfully created an event
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);

                    // closing this screen
                    getActivity().finish();
                } else {
                    // failed to create user
                    Log.d("failed to create event.", json.toString());

                }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}
