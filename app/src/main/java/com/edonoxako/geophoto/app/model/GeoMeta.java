package com.edonoxako.geophoto.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by EugeneM on 27.04.2015.
 */
public class GeoMeta implements Parcelable {
    private double longitude;
    private double latitude;

    public GeoMeta(LatLng data){
        this(data.latitude, data.longitude);
    }

    public GeoMeta(double ltd, double lng) {
        longitude = lng;
        latitude = ltd;
    }

    public GeoMeta(Parcel parcel) {
        double[] data = new double[2];
        parcel.readDoubleArray(data);
        longitude = data[0];
        latitude = data[1];
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDoubleArray(new double[]{longitude, latitude});
    }

    public static final Parcelable.Creator<GeoMeta> CREATOR = new Creator<GeoMeta>() {
        @Override
        public GeoMeta createFromParcel(Parcel source) {
            return new GeoMeta(source);
        }

        @Override
        public GeoMeta[] newArray(int size) {
            return new GeoMeta[size];
        }
    };
}
