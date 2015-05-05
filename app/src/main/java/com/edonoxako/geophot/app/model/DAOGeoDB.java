package com.edonoxako.geophot.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EugeneM on 05.05.2015.
 */
public class DAOGeoDB {
    //TODO: �������� ����������� ������� ������ �� �������, �������� ��, ����� �������� ����������� ������ ����� ������ �������
    private DBHelper dbHelper;

    public DAOGeoDB(Context context) {
        dbHelper = new DBHelper(context);
    }

    //����� �������� ������ � ������ �� ������� � ����������� �� � ������ �����
    public List<GeoData> read() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<GeoData> data = new ArrayList<GeoData>();
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                GeoData tmp = new GeoData();

                tmp.setLattitude(cursor.getDouble(cursor.getColumnIndex(DBHelper.LATITUDE)));
                tmp.setLongitude(cursor.getDouble(cursor.getColumnIndex(DBHelper.LONGITUDE)));
                tmp.setLastVisitedDate(cursor.getString(cursor.getColumnIndex(DBHelper.LAST_VISITED)));
                tmp.setText(cursor.getString(cursor.getColumnIndex(DBHelper.TEXT)));
                tmp.setImages(getImages());

                data.add(tmp);
            }
        }

        dbHelper.close();
        return data;
    }

    //����� ��������� ��� ��������, ��������������� ����������� ������, �� ������� ��������
    private List<Bitmap> getImages() {
        return new ArrayList<Bitmap>();
    }

    //����� ��������� ����� ������ � �������
    public void insert(GeoData data) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.LATITUDE, data.getLattitude());
        cv.put(DBHelper.LONGITUDE, data.getLongitude());
        cv.put(DBHelper.LAST_VISITED, data.getLastVisitedDate());
        cv.put(DBHelper.TEXT, data.getText());
        cv.put(DBHelper.IMAGE, 0);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(DBHelper.TABLE_NAME, null, cv);
        dbHelper.close();
    }
}
