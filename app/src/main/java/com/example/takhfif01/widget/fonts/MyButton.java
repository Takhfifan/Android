package com.example.takhfif01.widget.fonts;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class MyButton extends androidx.appcompat.widget.AppCompatButton {


    public MyButton(Context context) {
        super(context);
        init();

    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init(){

        if (!isInEditMode()){

            Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"Behdad.ttf");
            this.setTypeface(tf);

        }
    }
}
