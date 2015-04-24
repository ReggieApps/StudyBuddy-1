package com.example.adriangracia.studybuddy.fragment;

import android.content.Intent;
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

import com.example.adriangracia.studybuddy.AttendInformation;
import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.createEvent;
import com.example.adriangracia.studybuddy.objects.EventObject;

import java.util.ArrayList;

/**
 * Created by rgpaul on 4/20/2015.
 */
public class mainActivityFragment extends Fragment {

    final public String information = "information";

    public Spinner specifySubject;

    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<EventObject> eventList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);

        if(getActivity().getIntent().getSerializableExtra("EVENT")!=null) {
            EventObject newObj = (EventObject) getActivity().getIntent().getSerializableExtra("EVENT");
            eventList.add(newObj);
            list.add(eventList.get(eventList.indexOf(newObj)).getTitle());
        }


        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, list);

        ListView test = (ListView) v.findViewById(R.id.listView);
        test.setAdapter(adapter);


        test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Intent in = new Intent(getActivity(), AttendInformation.class);

                EventObject clickedEvent = eventList.get(position);

                String[] testInformation = {clickedEvent.getTo().toString(), clickedEvent.getLocation(), clickedEvent.getTitle(),clickedEvent.getDurationString(), clickedEvent.getDescription(), clickedEvent.getSubject()};
                in.putExtra(information ,testInformation);
                startActivity(in);
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
}

