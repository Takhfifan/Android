package com.example.takhfif01.widget.fonts;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

@SuppressLint("AppCompatCustomView")
public class MyEditText extends AppCompatEditText {



    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        if (!isInEditMode()){
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Behdad.ttf");
            this.setTypeface(tf);
        }
    }


}