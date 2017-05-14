package com.example.bittu.reminderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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

        Toolbar mtoolbar=(Toolbar)findViewById(R.id.toolbar);                                   //Toolbar
        mtoolbar.setTitle("All Saved Reminder");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ListView list=(ListView)findViewById(R.id.saved_list);
        handler=new DatabaseHandler(this);


        UserAdaptor userAdaptor=new UserAdaptor(this,handler.getAllReminderRecords());
        list.setAdapter(userAdaptor);








    }


}
