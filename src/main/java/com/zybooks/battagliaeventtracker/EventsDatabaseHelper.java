package com.zybooks.battagliaeventtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventsDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EVENTS = "events";
    private static final String COLUMN_ID = "_id";
    static final String COLUMN_EVENT_NAME = "event_name";

    private static final String CREATE_EVENTS_TABLE = "CREATE TABLE " +
            TABLE_EVENTS + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_EVENT_NAME + " TEXT" +
            ")";
    private String eventName;

    public EventsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    // Method to add a new event to the database
    public long addEvent(String eventName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_NAME, eventName);
        long result = db.insert(TABLE_EVENTS, null, values);
        db.close();
        return result; // Return the row ID of the newly inserted row, or -1 if an error occurred
    }

    // Method to retrieve all events from the database
    public Cursor getAllEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_EVENTS, null, null, null, null, null, null);
    }

    // Method to update the name of an event
    public int updateEventName(int eventId, String newEventName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_NAME, newEventName);
        return db.update(TABLE_EVENTS, values, COLUMN_ID + "=?", new String[]{String.valueOf(eventId)});
    }

    // Method to delete an event from the database
    public int deleteEvent(int eventId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_EVENTS, COLUMN_ID + "=?", new String[]{String.valueOf(eventId)});
    }
}
