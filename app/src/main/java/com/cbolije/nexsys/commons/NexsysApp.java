package com.cbolije.nexsys.commons;

import android.app.Application;
import android.content.Context;

public class NexsysApp extends Application {

    private static NexsysApp instance;

    public static  NexsysApp getApp() {
        return instance;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
