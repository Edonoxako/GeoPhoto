package com.edonoxako.geophot.app;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import com.edonoxako.geophot.app.fragments.MapViewFragment;
import com.edonoxako.geophot.app.model.DAOGeoDB;
import com.edonoxako.geophot.app.model.DBHelper;
import com.edonoxako.geophot.app.model.GeoData;

import java.util.List;

public class MainActivity extends Activity {
    public static final String TAG = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GeoData data = new GeoData(10, 15, "Привет", null, "18/06/1993");

        DAOGeoDB dao = new DAOGeoDB(this);
        dao.insert(data);
        List<GeoData> points = dao.read();

        readData(points);

        //Расскоментить для работы с представлениями
//        getFragmentManager().beginTransaction()
//                .add(R.id.content, new MapViewFragment())
//                .commit();
    }

    private void readData(List<GeoData> data) {
        for (GeoData d : data) {
            Log.d(TAG, "------------------------------");
            Log.d(TAG, "Longitude: " + d.getLongitude());
            Log.d(TAG, "Latitude: " + d.getLattitude());
            Log.d(TAG, "Text: " + d.getText());
            Log.d(TAG, "LastVisited: " + d.getLastVisitedDate());
        }
    }

}
