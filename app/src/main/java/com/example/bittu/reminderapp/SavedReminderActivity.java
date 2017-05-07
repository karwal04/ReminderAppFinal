package com.example.bittu.reminderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapter.UserAdaptor;
import Database.DatabaseHandler;
import Models.ReminderRecord;

public class SavedReminderActivity extends AppCompatActivity {

    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_reminder);


        ListView list=(ListView)findViewById(R.id.saved_list);
        handler=new DatabaseHandler(this);


        UserAdaptor userAdaptor=new UserAdaptor(this,handler.getAllReminderRecords());
        list.setAdapter(userAdaptor);



    }


}
