package com.example.anish.assistant.dataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by anish on 25-11-2016.
 */

public class AssistantOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "assistant.db";
    public static final int DB_VERSION = 1;
    private static AssistantOpenHelper instance;
    private static final String DATABASE_PATH = "/data/data/com.example.anish.assistant/databases/";

    public static AssistantOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new AssistantOpenHelper(context);
        }
        return instance;
    }

    public AssistantOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void createDataBase(Context context) {

        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase(context);
            } catch (IOException e) {
                e.printStackTrace();
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {

        File folder = new File(DATABASE_PATH);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File dbFile = new File(DATABASE_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase(Context context) throws IOException {

        InputStream myInput = context.getAssets().open(DB_NAME);
        String outFileName = DATABASE_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
