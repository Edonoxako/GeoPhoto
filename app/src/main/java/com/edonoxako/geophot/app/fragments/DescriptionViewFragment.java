package com.edonoxako.geophot.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.edonoxako.geophot.app.R;
import com.edonoxako.geophot.app.model.GeoMeta;

/**
 * Created by EugeneM on 27.04.2015.
 */
public class DescriptionViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.description_view, container, false);

        GeoMeta data = getArguments().getParcelable("data");

        ((TextView) v.findViewById(R.id.lng)).setText(String.valueOf(data.getLongitude()));
        ((TextView) v.findViewById(R.id.ltd)).setText(String.valueOf(data.getLatitude()));

        return v;
    }
}
