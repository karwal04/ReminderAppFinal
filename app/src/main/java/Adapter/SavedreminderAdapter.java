package Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.bittu.reminderapp.R;

import java.util.ArrayList;

import Models.ReminderRecord;

/**
 * Created by Bittu on 23-04-2017.
 */

public class SavedreminderAdapter extends ArrayAdapter<ReminderRecord>{

    public SavedreminderAdapter(Context context, ArrayList<ReminderRecord> rec) {
        super(context, 0, rec);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ReminderRecord user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_saved, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        TextView tvHome = (TextView) convertView.findViewById(R.id.Dob);
        // Populate the data into the template view using the data object
        tvName.setText(user.getName());
        tvHome.setText(user.getDate());
        // Return the completed view to render on screen
        return convertView;
    }
}
