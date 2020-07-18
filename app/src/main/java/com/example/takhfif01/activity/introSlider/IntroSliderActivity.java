package com.example.takhfif01.activity.introSlider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.takhfif01.R;
import com.example.takhfif01.activity.LoginActivity;

public class IntroSliderActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout layoutDots;


    SliderPagerAdapter pagerAdapter;

    Button btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        changeStatusBarColor();
        init();
        viewPager.setAdapter(pagerAdapter);
        showDots(viewPager.getCurrentItem());

        changeDotsColor();



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(IntroSliderActivity.this, LoginActivity.class));
                finish();
            }
        });


    }

    private void changeDotsColor() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                showDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void init() {
        viewPager =  findViewById(R.id.view_pager);
        layoutDots =  findViewById(R.id.layoutDots);
        btn_submit =  findViewById(R.id.btn_submit);
        pagerAdapter = new SliderPagerAdapter();
    }


    public class SliderPagerAdapter extends PagerAdapter {
        String [] slideTitles;
        String [] slideDescriptions;
        int [] bgColorIds = {R.color.slide_1_bg_color, R.color.slide_2_bg_color,
                R.color.slide_3_bg_color, R.color.slide_4_bg_color};
        int [] slideImageIds = {R.drawable.barg_64, R.drawable.barg_64,
                R.drawable.barg_64, R.drawable.barg_64};

        public SliderPagerAdapter(){
            slideTitles = getResources().getStringArray(R.array.slide_titles);
            slideDescriptions = getResources().getStringArray(R.array.slide_descriptions);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(IntroSliderActivity.this)
                    .inflate(R.layout.slide_intro_slider, container, false);
            view.findViewById(R.id.bgLayout).setBackgroundColor(
                    ContextCompat.getColor(IntroSliderActivity.this, bgColorIds[position]));
            ((ImageView) view.findViewById(R.id.slide_image)).setImageResource(slideImageIds[position]);
            ((TextView) view.findViewById(R.id.slide_title)).setText(slideTitles[position]);
            ((TextView) view.findViewById(R.id.slide_desc)).setText(slideDescriptions[position]);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return bgColorIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    private void showDots(int pageNumber){
        TextView [] dots = new TextView[viewPager.getAdapter().getCount()];
        layoutDots.removeAllViews();
        for(int i = 0; i < dots.length ; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            dots[i].setTextColor(ContextCompat.getColor(this,
                    (i == pageNumber ?  R.color.dot_active : R.color.dot_incative)
            ));
            layoutDots.addView(dots[i]);
        }
    }


}