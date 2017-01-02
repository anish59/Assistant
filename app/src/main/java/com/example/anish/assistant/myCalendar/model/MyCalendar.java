package com.example.anish.assistant.myCalendar.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anish.assistant.MyCalEventsModel;
import com.example.anish.assistant.dataBaseHelper.DatabaseManager;
import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anish on 29-12-2016.
 */
@AutoValue
public abstract class MyCalendar implements MyCalEventsModel {
    public static final Factory<MyCalendar> MY_CALENDAR_FACTORY = new Factory<>(AutoValue_MyCalendar::new);
    public static final RowMapper<MyCalendar> MY_CALENDAR_ROW_MAPPER = MY_CALENDAR_FACTORY.select_all_eventsMapper();

    public static List<MyCalendar> getAllEvents() {
        List<MyCalendar> myEvents = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(MyCalendar.SELECT_ALL_EVENTS, new String[]{});
        while (cursor.moveToNext()) {
            myEvents.add(MyCalendar.MY_CALENDAR_ROW_MAPPER.map(cursor));
        }
        DatabaseManager.getInstance().closeDatabase();
        return myEvents;
    }

    public static List<MyCalendar> getAllEventsDateWise(String date) {
        List<MyCalendar> myEvents = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        String query = String.format(MyCalendar.SELECT_ALL_EVENTS_BY_REMINDERDATE, date);
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            myEvents.add(MyCalendar.MY_CALENDAR_ROW_MAPPER.map(cursor));
        }
        DatabaseManager.getInstance().closeDatabase();
        return myEvents;
    }

    public static void insertInMyCalendar(MyCalRequest myCalRequest) {

        try {
            SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        /*sqLiteDatabase.insert(Book.TABLE_NAME, null,
                TodoNote.TODO_NOTE_FACTORY.marshal(todoNoteRequest).asContentValues());*/

            sqLiteDatabase.insert(MyCalendar.TABLE_NAME, null, MyCalendar.MY_CALENDAR_FACTORY.marshal()
                    .Title(myCalRequest.Title())
                    .Desctiption(myCalRequest.Desctiption())
                    .ReminderDate(myCalRequest.ReminderDate())
                    .ReminderDateMili(myCalRequest.ReminderDateMili())
                    .asContentValues());
            DatabaseManager.getInstance().closeDatabase();
        } catch (Exception e) {
            Log.e("INSERT_MyCal_EXP", e.toString());
        }
    }

    public static void update(MyCalRequest myCalRequest) { // For updating calender events
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        sqLiteDatabase.update(MyCalendar.TABLE_NAME, MyCalendar.MY_CALENDAR_FACTORY.marshal(myCalRequest).asContentValues(),
                MyCalendar.EVENTID + "=?", new String[]{String.valueOf(myCalRequest.EventId())});
        DatabaseManager.getInstance().closeDatabase();
    }

    public static void delete(long noteId) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        sqLiteDatabase.delete(MyCalendar.TABLE_NAME, MyCalendar.EVENTID + "=?", new String[]{String.valueOf(noteId)});
        DatabaseManager.getInstance().closeDatabase();
    }
}
