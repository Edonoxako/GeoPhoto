package com.edonoxako.geophot.app.model;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by EugeneM on 05.05.2015.
 */
public class GeoData {
    private int id;
    private double lattitude;
    private double longitude;
    private String text;
    private List<Bitmap> images;
    private String lastVisitedDate;

    public GeoData(){};

    public GeoData(double lng, double ltd, String txt, List<Bitmap> imgs, String lastVisited) {
        lattitude = ltd;
        longitude = lng;
        text = txt;
        images = imgs;
        lastVisitedDate = lastVisited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Bitmap> getImages() {
        return images;
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

    public String getLastVisitedDate() {
        return lastVisitedDate;
    }

    public void setLastVisitedDate(String lastVisitedDate) {
        this.lastVisitedDate = lastVisitedDate;
    }

}
