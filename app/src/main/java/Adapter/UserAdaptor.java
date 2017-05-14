package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bittu.reminderapp.BirthdayReminderActivity;
import com.example.bittu.reminderapp.R;
import com.example.bittu.reminderapp.SavedReminderActivity;

import Database.DatabaseHandler;

/**
 * Created by karwal on 21-04-2017.
 */

public class UserAdaptor extends CursorAdapter {

    DatabaseHandler handler;
    String name;



     public UserAdaptor(Context context, Cursor cursor){
        super(context,cursor,0);

    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        handler=new DatabaseHandler(context);
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);


    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        // Lookup view for data population
        TextView Name = (TextView) view.findViewById(R.id.name);
        TextView date = (TextView) view.findViewById(R.id.dateofbirth);
        TextView id=(TextView)view.findViewById(R.id.id);
        ImageView del_btn=(ImageView) view.findViewById(R.id.delete_btn);
        ImageView edit_btn=(ImageView)view.findViewById(R.id.edit_btn);



        // Populate the data into the template view using the data object
        name=cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String date_data=cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String id_data=cursor.getString(cursor.getColumnIndexOrThrow("_id"));

        Name.setText(name);
        date.setText(date_data);
        id.setText(id_data);
        // Return the completed view to render on screen



        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RelativeLayout vwParentRow = (RelativeLayout)view.getParent();
                TextView child = (TextView)vwParentRow.getChildAt(1);


                handler.deleteContact(child.getText().toString());
                Toast.makeText(context,child.getText().toString(),Toast.LENGTH_SHORT).show();


                Cursor cursor1=getCursor();
                changeCursor(handler.getAllReminderRecords());
            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout vwParentRow = (RelativeLayout)view.getParent();
                TextView Name = (TextView) vwParentRow.getChildAt(1);



                Intent intent=new Intent(context,BirthdayReminderActivity.class);
                intent.putExtra("id",Name.getText().toString());
                intent.putExtra("Update",1);

                view.getContext().startActivity(intent);

            }
        });
    }


}
