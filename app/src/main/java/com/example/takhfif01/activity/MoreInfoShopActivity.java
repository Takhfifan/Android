package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.takhfif01.R;
import com.example.takhfif01.fragment.moreInfo.AboutShopFragment;
import com.example.takhfif01.fragment.moreInfo.AddressShopFragment;
import com.example.takhfif01.fragment.moreInfo.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MoreInfoShopActivity extends AppCompatActivity {

    TabLayout tl_tabLayout;
    ViewPager vp_viewPager;
    List<Fragment> fragments;
    String[] titles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_shop);
        init();
        initViewPager();
        changeTabsFont();


    }

    private void init() {
        tl_tabLayout = findViewById(R.id.tl_tabLayout);
        vp_viewPager = findViewById(R.id.vp_viewPager);
    }


    private void initViewPager() {

        fragments = new ArrayList<>();
        fragments.add(AboutShopFragment.newInstance());
        fragments.add(AddressShopFragment.newInstance());

        titles = new String[]{"درباره ما", "آدرس"};

        vp_viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles));
        tl_tabLayout.setupWithViewPager(vp_viewPager);

    }

    private void changeTabsFont() {
        Typeface aGhasem = Typeface.createFromAsset(getAssets(), "Behdad.ttf");
        ViewGroup vg = (ViewGroup) tl_tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(aGhasem);
                }
            }
        }
    }


}