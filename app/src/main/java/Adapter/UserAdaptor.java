package Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.bittu.reminderapp.R;

/**
 * Created by karwal on 21-04-2017.
 */

public class UserAdaptor extends CursorAdapter {

     public UserAdaptor(Context context, Cursor cursor){
        super(context,cursor,0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Lookup view for data population
        TextView Name = (TextView) view.findViewById(R.id.name);
        TextView date = (TextView) view.findViewById(R.id.dateofbirth);
        TextView priority = (TextView) view.findViewById(R.id.Prio);
        // Populate the data into the template view using the data object
        String name=cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String date_data=cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String prio=cursor.getString(cursor.getColumnIndexOrThrow("priority"));

        Name.setText(name);
        date.setText(date_data);
        priority.setText(prio);
        // Return the completed view to render on screen
    }


}
