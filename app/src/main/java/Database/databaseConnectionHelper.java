package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Models.ReminderRecord;

/**
 * Created by Bittu on 16-04-2017.
 */

public class databaseConnectionHelper extends SQLiteOpenHelper {


    private static final String DB_NAME="Reminders";
    private static final int DB_VERSION=1;

    String KEY_NAME="Name";
    String KEY_DATE="Date";
    String KEY_PRIOPRITY="Priority";
    String KEY_VIBRATE="Vibrate";
    String KEY_TIME="Time";

    String TABLE_NAME="BirthdayReminder";



    public databaseConnectionHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table BirthdayReminder(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +"Name Text,"
                    +"Date NUMERIC,"
                    +"Priority INTEGER,"
                    +"Time NUMERIC);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS BirthdayReminder");
        db.execSQL("Create table BirthdayReminder(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"Name Text,"
                +"Date NUMERIC,"
                +"Priority INTEGER,"
                +"Vibrate NUMERIC,"
                +"Time NUMERIC);");
    }








    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addContact(ReminderRecord rec) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, rec.getName());
        values.put(KEY_DATE, rec.getDate());
        values.put(KEY_PRIOPRITY, rec.getPriority());
        values.put(KEY_TIME, rec.getDate());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }



    // Getting single contact
    ReminderRecord getContact(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_NAME,
                        KEY_DATE,KEY_PRIOPRITY}, KEY_NAME + "=?",
                new String[] {name}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ReminderRecord newRec=new ReminderRecord(cursor.getString(0),cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getColumnName(3)));

        // return contact
        return newRec;
    }

    // Getting All Contacts
    public List<ReminderRecord> getAllContacts() {
        List<ReminderRecord> contactList = new ArrayList<ReminderRecord>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ReminderRecord contact = new ReminderRecord();
                contact.setName(cursor.getString(0));
                contact.setDate(cursor.getString(1));
                contact.setTime(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(ReminderRecord contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_DATE, contact.getDate());

        // updating row
        return db.update(TABLE_NAME, values, KEY_NAME + " = ?",
                new String[] {contact.getName() });
    }

    // Deleting single contact
    public void deleteContact(ReminderRecord contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_NAME + " = ?",
                new String[] { contact.getName()});
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
