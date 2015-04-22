package com.example.adriangracia.studybuddy.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.NumberPicker;

import com.example.adriangracia.studybuddy.*;

/**
 * Created by jonathanmitchell on 4/20/15.
 */
public class ChooseDurationDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    public static final String EXTRA_DURATION = "_EXTRA_DURATION_";

    private NumberPicker numPick;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_choose_duration,null);

        numPick = (NumberPicker)v.findViewById(R.id.number_picker_choose_duration);

        numPick.setMinValue(1);
        numPick.setMaxValue(6);



        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Choose Duration")
                .setMessage("Duration in Hours:")
                .setCancelable(false)
                .setPositiveButton("Confirm",this)
                .setNegativeButton("Cancel",this)
                .create();

    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        switch(i){
            case DialogInterface.BUTTON_POSITIVE:
                if(numPick!=null){
                    int returnDur = numPick.getValue();

                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_DURATION,returnDur);

                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,intent);

                }

                dialogInterface.dismiss();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                dialogInterface.dismiss();
                break;
        }

    }
}
