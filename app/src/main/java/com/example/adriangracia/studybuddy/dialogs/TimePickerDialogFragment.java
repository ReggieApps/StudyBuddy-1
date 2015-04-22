package com.example.adriangracia.studybuddy.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.example.adriangracia.studybuddy.*;
import com.example.adriangracia.studybuddy.objects.TimeObject;

/**
 * Created by jonathanmitchell on 4/20/15.
 */
public class TimePickerDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    public static final String TIME_OBJ = "_TIME_OBJ_";

    private static final String TAG = "TimePickDFrag";

    TimePicker tp;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_time_picker,null);
        tp = (TimePicker)v.findViewById(R.id.time_picker);


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setMessage("Pick a Time!")
                .setCancelable(false)
                .setPositiveButton("Set!",this)
                .setNegativeButton("Cancel",this)
                .create();
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case DialogInterface.BUTTON_POSITIVE:

                Integer hour = tp.getCurrentHour();
                Integer min = tp.getCurrentMinute();

                Intent intent = new Intent();



                TimeObject to = new TimeObject(hour,min);

                intent.putExtra(TIME_OBJ,to);

                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,intent);

                dialogInterface.dismiss();

                break;
            case DialogInterface.BUTTON_NEGATIVE:
                dialogInterface.dismiss();
                break;
        }

    }
}
