package com.example.bittu.reminderapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Database.DatabaseHandler;
import Fragments.DatePickerFragment;
import Models.ReminderRecord;
import alarmManager.AlertReceiver;

public class BirthdayReminderActivity extends AppCompatActivity {

    public EditText name;
    public static EditText date;
    public SeekBar seek;
    int progressChangedValue;
    SwitchCompat mSwitchCompat;
    int id;


    DatabaseHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_reminder);

        Toolbar mtoolbar=(Toolbar)findViewById(R.id.toolbar);                                   //Toolbar
        mtoolbar.setTitle("Birthday Reminder");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        handler=new DatabaseHandler(getApplicationContext());







        name=(EditText)findViewById(R.id.name);
        date=(EditText)findViewById(R.id.date_picker);
        seek=(SeekBar)findViewById(R.id.seekbar);
        mSwitchCompat=(SwitchCompat)findViewById(R.id.switchCompat);


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


        if(getIntent().hasExtra("id")){
            String IntentId=getIntent().getExtras().getString("id");


            ReminderRecord ct=handler.getReminder(IntentId);


            id=ct.get_id();
            name.setText(ct.getName());
            date.setText(ct.getDate());
            mSwitchCompat.setChecked(ct.isVibrate());
            seek.setProgress(ct.getPriority());
        }

    }



    public void add_reminder(View view){                //OnClick method
        ReminderRecord rec=new ReminderRecord();
        rec.set_id(id);
        rec.setName(name.getText().toString());
        rec.setDate(date.getText().toString());
        rec.setVibrate(mSwitchCompat.isChecked());
        rec.setPriority(progressChangedValue);

        if(getIntent().hasExtra("id")) {
            handler.updateContact(rec);
            finish();

            Intent intent=new Intent(BirthdayReminderActivity.this,SavedReminderActivity.class);
            startActivity(intent);
        }
        else {
            handler.addReminder(rec);
            finish();
        }

        setAlarm();


    }

    private void setAlarm()
    {

        String[] date_format=date.getText().toString().split("/");

        int month=Integer.parseInt(date_format[1]);
        int day=Integer.parseInt(date_format[0]);



        Calendar myAlarmDate = Calendar.getInstance();
        myAlarmDate.setTimeInMillis(System.currentTimeMillis());
        myAlarmDate.set(2017, month, day, 17, 20, 0);

        Intent alertIntent=new Intent(this, AlertReceiver.class);

        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,myAlarmDate.getTimeInMillis(), PendingIntent.getBroadcast(this,1,alertIntent,PendingIntent.FLAG_UPDATE_CURRENT));

        Toast.makeText(this, "Alarm Scheduled for"+ month+"  "+ day, Toast.LENGTH_LONG).show();

    }





    public void date_picker(View view){
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");


    }


}

