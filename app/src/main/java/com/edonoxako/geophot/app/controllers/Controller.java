package com.edonoxako.geophot.app.controllers;

import android.app.Activity;

/**
 * Created by EugeneM on 27.04.2015.
 */
public abstract class Controller {

    protected Activity activity;

    public Controller(Activity a) {
        activity = a;
    }
}
