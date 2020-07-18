package com.example.takhfif01.activity.introSlider;

import android.content.Context;
import android.content.SharedPreferences;

public class SliderPreManger {

    private Context context;
    private SharedPreferences preferences;

    private static final String PREF_NAME = "slider-pref";
    private static final String KEY_START = "startslider";


    public SliderPreManger(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean getStartSlider(){
        return preferences.getBoolean(KEY_START, true);
    }

    public void setStartSlider(Boolean start){
        preferences.edit().putBoolean(KEY_START, start).apply();
    }



}
