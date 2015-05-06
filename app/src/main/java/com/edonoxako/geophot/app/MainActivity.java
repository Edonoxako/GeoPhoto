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

        DAOGeoDB dao = new DAOGeoDB(this);

        GeoData point1 = new GeoData(140.4, 51.6, "Wai wai", null, "01.12.1270");
        GeoData point2 = new GeoData(760.1, 9.67, "LOL", null, "12.11.1861");

//        dao.insert(point1);
//        dao.insert(point2);
        Log.d(TAG, "ALL");
        readData(dao.read());

        Log.d(TAG, "SINGLE");
        GeoData data = dao.read(5);
        readSingle(data);



        //–асскоментить дл€ работы с представлени€ми
//        getFragmentManager().beginTransaction()
//                .add(R.id.content, new MapViewFragment())
//                .commit();
    }

    private void readData(List<GeoData> data) {
        for (GeoData d : data) {
            readSingle(d);
        }
    }

    private void readSingle(GeoData data) {
        Log.d(TAG, "------------------------------");
        Log.d(TAG, "ID: " + data.getId());
        Log.d(TAG, "Longitude: " + data.getLongitude());
        Log.d(TAG, "Latitude: " + data.getLattitude());
        Log.d(TAG, "Text: " + data.getText());
        Log.d(TAG, "LastVisited: " + data.getLastVisitedDate());
        Log.d(TAG, "------------------------------");
    }

}
