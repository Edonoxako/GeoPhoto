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
    //TODO: Добавить логику чтенния из таблицы с изображениями
    private DBHelper dbHelper;

    public DAOGeoDB(Context context) {
        dbHelper = new DBHelper(context);
    }


    //Метод получает данные о точках из таблицы и упаковывает их в список точек
    public List<GeoData> read() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<GeoData> data = new ArrayList<GeoData>();
        if (cursor.moveToFirst()) {
            do {
                GeoData tmp = packToPointDataObject(cursor);
                data.add(tmp);
            } while (cursor.moveToNext());
        }

        dbHelper.close();
        return data;
    }

    //Метод для получения конкретной точки по её id
    public GeoData read(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, "ID = ?", new String[] {String.valueOf(id)}, null, null, null);
        DBHelper.readCursor(cursor);
        GeoData data = null;
        if (cursor.moveToFirst()) {
            data = packToPointDataObject(cursor);
        }

        dbHelper.close();
        return data;
    }

    //Вспомогательный метод, упаковывает строку из таблицы в объект, содержащий информацию о точке
    private GeoData packToPointDataObject(Cursor cursor) {
        GeoData tmp = new GeoData();
        tmp.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.ID)));
        tmp.setLattitude(cursor.getDouble(cursor.getColumnIndex(DBHelper.LATITUDE)));
        tmp.setLongitude(cursor.getDouble(cursor.getColumnIndex(DBHelper.LONGITUDE)));
        tmp.setLastVisitedDate(cursor.getString(cursor.getColumnIndex(DBHelper.LAST_VISITED)));
        tmp.setText(cursor.getString(cursor.getColumnIndex(DBHelper.TEXT)));
        tmp.setImages(getImages());
        return tmp;
    }

    //Метод считывает все картинки, соответствующие определённой записи, из таблицы картинок(пока заглушка)
    private List<Bitmap> getImages() {
        return new ArrayList<Bitmap>();
    }

    //Метод добавляет новую точку в таблицу
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

    //Удаляет запись о точке из базы данных
    public void delete(GeoData data) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE_NAME, "ID = ?", new String[]{String.valueOf(data.getId())});
        dbHelper.close();
    }

    //Изменяет информацию о точке в таблице
    public void update(GeoData data) {
        //TODO: Добавить изменение изображений
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.LATITUDE, data.getLattitude());
        cv.put(DBHelper.LONGITUDE, data.getLongitude());
        cv.put(DBHelper.TEXT, data.getText());
        cv.put(DBHelper.LAST_VISITED, data.getLastVisitedDate());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(DBHelper.TABLE_NAME, cv, "ID = ?", new String[] {String.valueOf(data.getId())});
        dbHelper.close();
    }
}
