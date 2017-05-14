package com.example.bittu.reminderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mtoolbar=(Toolbar)findViewById(R.id.toolbar);                                   //Toolbar
        mtoolbar.setTitle("Home");




                                                                                                //Toolbar Menu Innflation and onClick function
        mtoolbar.inflateMenu(R.menu.menu_main);
        mtoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.Hot_reminder:
                        Intent intent=new Intent(MainActivity.this,SavedReminderActivity.class);
                        startActivity(intent);
                        break;
                    case  R.id.savedReminder:
                        Toast.makeText(MainActivity.this,"saved",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }

                return true;
            }
        });

    }

    public void bdayReminder(View view){
        Intent intent=new Intent(this,BirthdayReminderActivity.class);
        startActivity(intent);
    }

}
