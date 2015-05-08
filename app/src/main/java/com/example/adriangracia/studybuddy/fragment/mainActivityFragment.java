package com.example.adriangracia.studybuddy.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.createEvent;
import com.example.adriangracia.studybuddy.factories.JSONParser;
import com.example.adriangracia.studybuddy.objects.EventObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class mainActivityFragment extends Fragment {

    final public String information = "information";

    public Spinner specifySubject;

    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<EventObject> eventList = new ArrayList<>();

    JSONParser jsonParser = new JSONParser();

    // url to create new product
    private static String url_get_event = "http://isumobileclub.ilstu.edu/get_events.php";

    private ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, list);
        new CreateNewProduct().execute();
        ListView test = (ListView) v.findViewById(R.id.listView);
        test.setAdapter(adapter);


        test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

            }
        });

        Button createEventButton = (Button) v.findViewById(R.id.Button2);

        createEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            Intent in = new Intent(getActivity(), createEvent.class);

            startActivity(in);
        }});


        specifySubject = (Spinner) v.findViewById(R.id.spinner);


       return v;
    }

    class CreateNewProduct extends AsyncTask<String, String, String> {


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
                    list.add(object.getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }


        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}

