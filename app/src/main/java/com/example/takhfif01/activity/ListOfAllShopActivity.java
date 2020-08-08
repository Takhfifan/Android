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
import com.example.takhfif01.fragment.listofallshop.EducationFragment;
import com.example.takhfif01.fragment.listofallshop.MakeupFragment;
import com.example.takhfif01.fragment.listofallshop.MedicalFragment;
import com.example.takhfif01.fragment.listofallshop.RestaurantFragment;
import com.example.takhfif01.fragment.listofallshop.ServicesFragment;
import com.example.takhfif01.fragment.listofallshop.SportiveFragment;
import com.example.takhfif01.fragment.listofallshop.TheaterFragment;
import com.example.takhfif01.fragment.listofallshop.TourFragment;
import com.example.takhfif01.fragment.moreInfo.AboutShopFragment;
import com.example.takhfif01.fragment.moreInfo.AddressShopFragment;
import com.example.takhfif01.fragment.moreInfo.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ListOfAllShopActivity extends AppCompatActivity {

    TabLayout tl_tabLayout;
    ViewPager vp_viewPager;
    List<Fragment> fragments;
    String[] titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_all_shop);
        init();
        initViewPager();
        changeTabsFont();
    }


    private void init() {
        tl_tabLayout = findViewById(R.id.tabLayout_list_off_all_shop);
        vp_viewPager = findViewById(R.id.viewPager_list_off_all_shop);


    }


    private void initViewPager() {

        fragments = new ArrayList<>();


        /*fragments.add(RestaurantFragment.newInstance());
        fragments.add(SportiveFragment.newInstance());
        fragments.add(ServicesFragment.newInstance());
        fragments.add(MedicalFragment.newInstance());
        fragments.add(TheaterFragment.newInstance());
        fragments.add(MakeupFragment.newInstance());
        fragments.add(EducationFragment.newInstance());
        fragments.add(TourFragment.newInstance());

        titles = new String[]{"رستوران و کافی شاپ", "تفریحی و ورزشی"
                ,"خدمات","سلامتی و پزشکی"
                ,"هنر و تئاتر","زیبایی و آرایشی"
                ,"آموزش","تور و سفر"
        };*/
        fragments.add(TourFragment.newInstance());
        fragments.add(EducationFragment.newInstance());
        fragments.add(MakeupFragment.newInstance());
        fragments.add(TheaterFragment.newInstance());
        fragments.add(MedicalFragment.newInstance());
        fragments.add(ServicesFragment.newInstance());
        fragments.add(SportiveFragment.newInstance());
        fragments.add(RestaurantFragment.newInstance());
        titles = new String[]{"تور و سفر", "آموزش"
                ,"زیبایی و آرایشی","هنر و تئاتر"
                ,"سلامتی و پزشکی","خدمات"
                ,"تفریحی و ورزشی","رستوران و کافی شاپ"
        };

        vp_viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles));
        tl_tabLayout.setupWithViewPager(vp_viewPager);
        vp_viewPager.setCurrentItem(8);

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