package com.edonoxako.geophot.app.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by EugeneM on 23.04.2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = "DB";

    public static final String DB_NAME = "GeoDB";
    public static final String TABLE_NAME = "GeoData";

    //Columns' names
    public static final String ID = "id";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String LAST_VISITED = "lastVisited";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //—оздаЄм таблицу дл€ хранени€ информации о точках на карте
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LATITUDE + " REAL, " +
                LONGITUDE + " REAL, " +
                TEXT + " TEXT, " +
                IMAGE + " INTEGER, " +
                LAST_VISITED + " TEXT);");

        //TODO: —оздать таблицу дл€ хранени€ ссылок на изображени€, относ€щиес€ к точкам на карте

        Log.d(TAG, "DB created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //¬спомогательный метод, выводит содержани€ курсора в лог
    public static void readCursor(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
//            for (String name : cursor.getColumnNames()) {
//                Log.d(TAG, name);
//            }
            do {
                Log.d(TAG, "###########################");
                Log.d(TAG, "ID: " + String.valueOf(cursor.getInt(cursor.getColumnIndex(ID))));
                Log.d(TAG, "LONGITUDE: " + String.valueOf(cursor.getDouble(cursor.getColumnIndex(LONGITUDE))));
                Log.d(TAG, "LATITUDE: " + String.valueOf(cursor.getDouble(cursor.getColumnIndex(LATITUDE))));
                Log.d(TAG, "TEXT: " + String.valueOf(cursor.getString(cursor.getColumnIndex(TEXT))));
                Log.d(TAG, "LAST: " + String.valueOf(cursor.getString(cursor.getColumnIndex(LAST_VISITED))));
                Log.d(TAG, "###########################");
            } while (cursor.moveToNext());
        } else {
            Log.d(TAG, "Cursor is empty");
        }
    }
}
