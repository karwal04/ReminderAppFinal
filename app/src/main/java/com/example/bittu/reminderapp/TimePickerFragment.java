package com.example.bittu.reminderapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;
import android.text.format.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Bittu on 15-04-2017.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        return new TimePickerDialog(getActivity(),this,hour,minute,DateFormat.is24HourFormat(getActivity()));


    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1 ) {
        BirthdayReminderActivity.time.setText(i+":"+i1);



    }
}
