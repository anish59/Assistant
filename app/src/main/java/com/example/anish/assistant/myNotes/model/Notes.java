package com.example.anish.assistant.myNotes.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anish.assistant.dataBaseHelper.DatabaseManager;
import com.example.anish.assistant.notesModel;
import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anish on 25-11-2016.
 */

@AutoValue
public abstract class Notes implements notesModel {
    public Notes() {
    }

    public static final Factory<Notes> FACTORY = new Factory<>(AutoValue_Notes::new);

    public static final RowMapper<Notes> NOTES_ROW_MAPPER = FACTORY.select_all_notesMapper();

    public static void insertInNotes(NotesRequest notesRequest) {

        try {
            SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        /*sqLiteDatabase.insert(Book.TABLE_NAME, null,
                TodoNote.TODO_NOTE_FACTORY.marshal(todoNoteRequest).asContentValues());*/

            sqLiteDatabase.insert(Notes.TABLE_NAME, null, Notes.FACTORY.marshal()
                    .Title(notesRequest.Title())
                    .Description(notesRequest.Description())
                    .NoteDate(notesRequest.NoteDate())
                    .NoteDateMili(notesRequest.NoteDateMili())
                    .asContentValues());
            DatabaseManager.getInstance().closeDatabase();
        } catch (Exception e) {
            Log.e("INSERT_NOTES_EXP", e.toString());

        }
    }

    public static List<Notes> getAllNotes() {
        List<Notes> notes = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(Notes.SELECT_ALL_NOTES, new String[]{});
        while (cursor.moveToNext()) {
            notes.add(Notes.NOTES_ROW_MAPPER.map(cursor));
        }
        DatabaseManager.getInstance().closeDatabase();
        return notes;
    }

    public static List<Notes> getAllNotesByDate(String date) {
        List<Notes> notes = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        String query = String.format(Notes.SELECT_ALL_NOTES_BY_NOTEDATE, date);
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            notes.add(Notes.NOTES_ROW_MAPPER.map(cursor));
        }
        DatabaseManager.getInstance().closeDatabase();

        return notes;
    }

    public static void update(NotesRequest notesRequest) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        sqLiteDatabase.update(Notes.TABLE_NAME, Notes.FACTORY.marshal(notesRequest).asContentValues(),
                Notes.NOTEID + "=?", new String[]{String.valueOf(notesRequest.NoteId())});
        DatabaseManager.getInstance().closeDatabase();
    }


    public static void delete(long noteId) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        sqLiteDatabase.delete(Notes.TABLE_NAME, Notes.NOTEID + "=?", new String[]{String.valueOf(noteId)});
        DatabaseManager.getInstance().closeDatabase();
    }
}
/*
    public static Notes getAllNotesByNoteId(Long noteId) {
        Notes noteData = null;
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(Notes.SELECT_ALL_NOTES_BY_NOTEID, new String[]{String.valueOf(noteId)});

        if (cursor != null) {
            cursor.moveToFirst();
            noteData = Notes.NOTES_ROW_MAPPER.map(cursor);
        }
        DatabaseManager.getInstance().closeDatabase();
        return noteData;
    }*/