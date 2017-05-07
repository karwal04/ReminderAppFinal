package com.example.bittu.reminderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import Database.databaseConnectionHelper;
import Models.ReminderRecord;

public class BirthdayReminderActivity extends AppCompatActivity {

    public EditText name;
    public static EditText date;
    public static EditText time;
    public SeekBar seek;
    int progressChangedValue;
    databaseConnectionHelper db=new databaseConnectionHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_reminder);




        name=(EditText)findViewById(R.id.name);
        date=(EditText)findViewById(R.id.date_picker);
        time=(EditText)findViewById(R.id.time_picker);
        seek=(SeekBar)findViewById(R.id.seekbar);


        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(BirthdayReminderActivity.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void add_reminder(View view){

        ReminderRecord newrec=new ReminderRecord(name.getText().toString(),date.getText().toString(),time.getText().toString(),progressChangedValue);
        //Toast.makeText(this,Integer.toString(newrec.getPriority()),Toast.LENGTH_SHORT).show();

        db.addContact(newrec);

    }





    public void date_picker(View view){
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void time_picker(View view){
        TimePickerFragment newFragmen=new TimePickerFragment();
        newFragmen.show(getFragmentManager(),"timePicker");
    }
}

