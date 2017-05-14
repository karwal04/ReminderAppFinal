package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Models.ReminderRecord;

/**
 * Created by karwal on 17-04-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;

    // Database Name
    private static final String DATABASE_NAME = "ReminderApp";

    // ReminderRecords table name
    private static final String TABLE_REMINDER = "Test2";

    // ReminderRecords Table Columns name
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE= "date";
    private static final String KEY_VIBRATE="vibrate";
    private static final String KEY_PRIORITY="priority";


    public DatabaseHandler(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);

        
    }

    String CREATE_ReminderRecordS_TABLE = "CREATE TABLE " + TABLE_REMINDER + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_DATE + " TEXT,"
            + KEY_VIBRATE + " INTEGER,"
            + KEY_PRIORITY + " INTEGER "+")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_REMINDER;





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ReminderRecordS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(CREATE_ReminderRecordS_TABLE);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(CREATE_ReminderRecordS_TABLE);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new ReminderRecord
    public void addReminder(ReminderRecord rem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, rem.getName()); // ReminderRecord Name
        values.put(KEY_DATE, rem.getDate());
        values.put(KEY_VIBRATE,rem.isVibrate());
        values.put(KEY_PRIORITY,rem.getPriority());

        // Inserting Row
        db.insert(TABLE_REMINDER, null, values);
        db.close(); // Closing database connection
    }

    // Getting single ReminderRecord
    public ReminderRecord getReminder(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REMINDER, new String[] { KEY_ID,
                        KEY_NAME,KEY_DATE,KEY_VIBRATE,KEY_PRIORITY}, KEY_ID+ "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();




        ReminderRecord rec = new ReminderRecord(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getInt(3),Integer.parseInt(cursor.getString(4)));
        // return contact
        String jj="0";

        Log.v("BOOOOOOOLLLEAANNN",String.valueOf(Boolean.valueOf(jj)));
        return rec;
    }


    //updating single record

    public int updateContact(ReminderRecord contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_DATE,contact.getDate());
        values.put(KEY_VIBRATE,contact.isVibrate());
        values.put(KEY_PRIORITY,contact.getPriority());

        // updating row
        return db.update(TABLE_REMINDER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
    }


    //delete single record

    public void deleteContact(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REMINDER, KEY_ID + " = ?",
                new String[] {String.valueOf(id)});
        db.close();
    }









    // Getting All ReminderRecords
    public Cursor getAllReminderRecords() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REMINDER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // return ReminderRecord list
        return cursor;
    }




    // Getting ReminderRecords Count
    public int getReminderRecordsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_REMINDER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }












}
