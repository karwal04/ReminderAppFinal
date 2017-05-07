package com.example.bittu.reminderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapter.SavedreminderAdapter;
import Models.ReminderRecord;
import Database.databaseConnectionHelper;

public class SavedReminderActivity extends AppCompatActivity {

    databaseConnectionHelper db=new databaseConnectionHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_reminder);



        ArrayList<ReminderRecord> arrayOfUsers = new ArrayList<ReminderRecord>();
        SavedreminderAdapter adapter = new SavedreminderAdapter(this, arrayOfUsers);
        ListView listView = (ListView) findViewById(R.id.saved_list);
        listView.setAdapter(adapter);

        List<ReminderRecord> rec=db.getAllContacts();

        for(ReminderRecord temp: rec){

            adapter.add(temp);
        }


    }
}
