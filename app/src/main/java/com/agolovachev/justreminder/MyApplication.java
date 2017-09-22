package com.agolovachev.justreminder;

import android.app.Application;

/**
 * Created by Andrew on 22.09.2017.
 */

public class MyApplication extends Application {

    private static boolean activityVisible;

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }
}
