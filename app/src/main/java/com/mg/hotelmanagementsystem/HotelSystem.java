package com.mg.hotelmanagementsystem;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by moses on 7/11/18.
 */

public class HotelSystem extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }
}
