package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.takhfif01.R;
import com.example.takhfif01.activity.introSlider.IntroSliderActivity;
import com.example.takhfif01.activity.introSlider.SliderPreManger;

public class SplashActivity extends AppCompatActivity {

    SliderPreManger preManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preManger = new SliderPreManger(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (preManger.getStartSlider()){
                    Intent intent = new Intent(SplashActivity.this, IntroSliderActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);


    }
}