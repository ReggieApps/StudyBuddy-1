package com.example.adriangracia.studybuddy.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adriangracia.studybuddy.AttendInformation;
import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.createEvent;
import com.example.adriangracia.studybuddy.factories.JSONParser;
import com.example.adriangracia.studybuddy.objects.EventObject;
import com.example.adriangracia.studybuddy.objects.TimeObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class mainActivityFragment extends Fragment {

    final public String information = "information";

    public Spinner specifySubject;

    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<EventObject> eventList = new ArrayList<>();

    JSONParser jsonParser = new JSONParser();

    ListView test;
    ArrayAdapter adapter;

    // url to create new product
    private static String url_get_event = "http://isumobileclub.ilstu.edu/get_events.php";

    private ProgressDialog pDialog;

    AsyncTask task;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_main, container, false);
        adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, list);
        test = (ListView) v.findViewById(R.id.listView);

        task = new CreateNewProduct(this){
            @Override
            public void onResponseReceived(String result) {

            }
        }.execute();


        test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Intent in = new Intent(getActivity(), AttendInformation.class);

                EventObject clickedEvent = eventList.get(position);

                String[] testInformation = {clickedEvent.getTo().toString(), clickedEvent.getLocation(), clickedEvent.getTitle(), clickedEvent.getDurationString(), clickedEvent.getDescription(), clickedEvent.getSubject()};
                in.putExtra(information, testInformation);
                startActivity(in);
            }
        });

        Button createEventButton = (Button) v.findViewById(R.id.Button2);

        createEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), createEvent.class);

                startActivity(in);
            }
        });

        specifySubject = (Spinner) v.findViewById(R.id.spinner);
        specifySubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new CreateNewProduct(view){

                    @Override
                    public void onResponseReceived(String result) {

                    }
                }.execute();

                if (position == 0) {

                } else {
                    String selectedSubj = getResources().getStringArray(R.array.class_array)[position];
                    for (int i = 0; i < eventList.size(); i++) {
                        if (!eventList.get(i).getSubject().equals(selectedSubj)) {
                            list.remove(list.indexOf(eventList.get(i).getTitle()));
                            eventList.remove(i);
                            i--;
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button refresh = (Button) v.findViewById(R.id.leftButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new CreateNewProduct(v){

                    @Override
                    public void onResponseReceived(String result) {

                    }
                }.execute();

                if (specifySubject.getSelectedItemPosition() == 0) {

                } else {
                    String selectedSubj = getResources().getStringArray(R.array.class_array)[specifySubject.getSelectedItemPosition()];
                    for (int i = 0; i < eventList.size(); i++) {
                        if (!eventList.get(i).getSubject().equals(selectedSubj)) {
                            list.remove(list.indexOf(eventList.get(i).getTitle()));
                            eventList.remove(i);
                            i--;
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

       return v;
    }

    abstract class CreateNewProduct extends AsyncTask<String, String, String> implements Handler.Callback{

        mainActivityFragment caller;
        View v;

        public CreateNewProduct(mainActivityFragment caller) {
            this.caller = caller;
        }

        public CreateNewProduct(View v){
            this.v = v;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Getting Events...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            JSONArray jsonArr = jsonParser.getJSONFromUrl(url_get_event);
            for(int n = 0; n < jsonArr.length(); n++)
            {
                try {
                    JSONObject object = jsonArr.getJSONObject(n);
                    if(!list.contains(object.getString("title"))){
                    String[] time = object.getString("time").split(":");
                    time[1] = time[1].substring(0, 2);
                    EventObject tempEven = new EventObject(object.getString("title"), object.getString("location"), object.getString("description"), object.getString("subject"), 0, new TimeObject(Integer.parseInt(time[0]), Integer.parseInt(time[1])));
                    eventList.add(tempEven);
                    list.add(object.getString("title"));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            test.setAdapter(adapter);
            pDialog.dismiss();
            onResponseReceived(file_url);
        }

        public abstract void onResponseReceived(String result);

        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    }
}

