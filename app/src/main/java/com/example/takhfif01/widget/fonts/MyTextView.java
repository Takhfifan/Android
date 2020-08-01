package com.example.takhfif01.widget.fonts;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class MyTextView extends AppCompatTextView {

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
