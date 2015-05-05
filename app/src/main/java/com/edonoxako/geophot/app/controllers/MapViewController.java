package com.edonoxako.geophot.app.controllers;

import android.app.Activity;
import android.os.Bundle;
import com.edonoxako.geophot.app.R;
import com.edonoxako.geophot.app.fragments.DescriptionViewFragment;
import com.edonoxako.geophot.app.model.GeoMeta;

/**
 * Created by EugeneM on 27.04.2015.
 */
public class MapViewController extends Controller {
    public MapViewController(Activity a) {
        super(a);
    }

    public void openDecriptionView(GeoMeta data) {

        Bundle args = new Bundle();
        args.putParcelable("data", data);

        DescriptionViewFragment descriptionViewFragment = new DescriptionViewFragment();
        descriptionViewFragment.setArguments(args);

        activity.getFragmentManager().beginTransaction()
                .replace(R.id.content, descriptionViewFragment)
                .addToBackStack("description")
                .commit();
    }
}
