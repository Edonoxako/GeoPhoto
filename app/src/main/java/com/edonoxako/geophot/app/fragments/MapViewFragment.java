package com.edonoxako.geophot.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.edonoxako.geophot.app.R;
import com.edonoxako.geophot.app.controllers.MapViewController;
import com.edonoxako.geophot.app.model.GeoMeta;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by EugeneM on 27.04.2015.
 */
public class MapViewFragment extends Fragment {
    private GoogleMap map;

    private TextView longitudeText;
    private TextView latitudeText;

    private String LONG = "Longitude: ";
    private String LAT = "Latitude: ";

    private MapViewController controller;

    private View view;

    //TODO: Реализовать восстановление данных после изменения конфигурации
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if (view != null) {
//            ViewGroup parent = (ViewGroup) view.getParent();
//            if (parent != null)
//                parent.removeView(view);
//        }
//        try {
        view = inflater.inflate(R.layout.map_view, container, false);
//        } catch (InflateException e) {
//
//        }

        controller = new MapViewController(getActivity());

        longitudeText = (TextView) view.findViewById(R.id.longitudeText);
        latitudeText = (TextView) view.findViewById(R.id.latitudeText);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        map = mapFragment.getMap();

        setMapListeners();

        return view;
    }

   /* @Override
    public void onDestroyView() {
        super.onDestroyView();
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            getFragmentManager().beginTransaction()
                    .remove(mapFragment)
                    .commit();
        }
    }*/

    private void setMapListeners() {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                longitudeText.setText(LONG + latLng.longitude);
                latitudeText.setText(LAT + latLng.latitude);
            }
        });

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                map.addMarker(new MarkerOptions().position(latLng).title("Hello"));
            }
        });

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                controller.openDecriptionView(new GeoMeta(marker.getPosition()));
                return true;
            }
        });
    }
}
