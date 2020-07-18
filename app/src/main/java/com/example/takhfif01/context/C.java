package com.example.takhfif01.context;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


@SuppressLint("Registered")
public class C extends Application {

    @SuppressLint("StaticFieldLeak")
    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

    }
}
