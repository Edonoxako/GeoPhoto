package com.edonoxako.geophoto.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity implements View.OnClickListener {

    private MapFragment mapFragment;
    private GoogleMap map;

    private TextView longitudeText;
    private TextView latitudeText;

    private String LONG = "Longitude: ";
    private String LAT = "Latitude: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.testBtn).setOnClickListener(this);
        longitudeText = (TextView) findViewById(R.id.longitudeText);
        latitudeText = (TextView) findViewById(R.id.latitudeText);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        map = mapFragment.getMap();
        if (map == null) {
            finish();
            return;
        }

        init();
    }

    @Override
    public void onClick(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    public void init() {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                longitudeText.setText(LONG + latLng.longitude);
                latitudeText.setText(LAT + latLng.latitude);
            }
        });
    }
}
