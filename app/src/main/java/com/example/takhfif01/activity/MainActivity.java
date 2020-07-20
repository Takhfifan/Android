package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.takhfif01.context.C;
import com.example.takhfif01.R;
import com.example.takhfif01.activity.introSlider.SliderPreManger;
import com.example.takhfif01.adapter.buyMenuAdapter;
import com.example.takhfif01.adapter.productMenuAdaapter;
import com.example.takhfif01.adapter.settingMenuAdapter;
import com.example.takhfif01.model.Image_Slider_Main;
import com.example.takhfif01.model.buyMenuListItem;
import com.example.takhfif01.model.productMenuListItem;
import com.example.takhfif01.model.settingMenuListItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {


    ArrayList<buyMenuListItem> buyMenuAdapterArrayList;
    ArrayList<productMenuListItem> productItems;
    ArrayList<settingMenuListItem> settingItems;

    ImageView btnMenu_main, imgArrowInfo_nav;
    DrawerLayout drawerLayout;
    ListView BuyMenuListView_nav, productListView_nav, settingListView;
    LinearLayout linearInfo_main, linearMoreInfo_main;

/*
    ArrayList<String> urlPics;
    ArrayList<String> name;*/

    private TextView txtUserName_nav,txtexit_nav;

    public static SharedPreferences preferences;

    private SliderPreManger preManeger;

    private CardView btn_goto_profile_main;




    private ViewPager viewPager_image_slider_main;
    private LinearLayout layout_dots_image_slider_main;
    private AdapterImageSliderMain adapterImageSliderMain;
    private Runnable runnable_image_slider_main = null;
    private Handler handler_image_slider_main = new Handler();

    private static String[] array_image_image_slider_main = {
            "https://png.pngtree.com/thumb_back/fw800/back_our/20190622/ourmid/pngtree-gorgeous-technology-dot-line-structure-banner-background-image_210889.jpg",
            "https://png.pngtree.com/thumb_back/fw800/back_our/20190622/ourmid/pngtree-gorgeous-technology-dot-line-structure-banner-background-image_210889.jpg",
            "https://png.pngtree.com/thumb_back/fw800/back_our/20190622/ourmid/pngtree-gorgeous-technology-dot-line-structure-banner-background-image_210889.jpg",
            "https://png.pngtree.com/thumb_back/fw800/back_our/20190622/ourmid/pngtree-gorgeous-technology-dot-line-structure-banner-background-image_210889.jpg",
    };



   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String username = bundle.getString("username");
            txtUserName_nav.setText(username);

            Log.i("TAG", "onActivityResult: username" + username);

            preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", username);
            editor.commit();

        }
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setUpUsernameInNav();
        checkUsernameInNav();
        exitProfile();
        setupSlider();
        //disableScrollingListViewInNav();
        addNavMenuList();
        openDrawer();
        showMoreInfoInNav();
        goToProfile();


    }


    private void init() {
        btnMenu_main = findViewById(R.id.btnMenu_main);
        drawerLayout = findViewById(R.id.drawer_main);
        BuyMenuListView_nav = findViewById(R.id.BuyMenuListView_nav);
        linearInfo_main = findViewById(R.id.linearInfo_main);
        linearMoreInfo_main = findViewById(R.id.linearMoreInfo_main);
        imgArrowInfo_nav = findViewById(R.id.imgArrowInfo_nav);
        imgArrowInfo_nav.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_48);
        productListView_nav = findViewById(R.id.productListView_nav);
        settingListView = findViewById(R.id.settingListView);
        txtUserName_nav = findViewById(R.id.txtUserName_nav);
        txtexit_nav = findViewById(R.id.txtexit_nav);
        preManeger = new SliderPreManger(C.context);
        btn_goto_profile_main = findViewById(R.id.btn_goto_profile_main);


    }
    private void goToProfile() {
        btn_goto_profile_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void exitProfile() {
        txtexit_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialogForExitProfile();
            }
        });

    }


    private void checkUsernameInNav() {
        if (txtUserName_nav.getText().toString().equals("ورود")) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            //startActivityForResult(intent, 0);
            startActivity(intent);
            finish();
        }
    }

    private void setUpUsernameInNav() {
        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = preferences.getString("username", "ورود");

        txtUserName_nav.setText(username);
        Log.i("TAG", "setUpUsernameInNav: username" + username);

        if (username != null) {
            if (username.equals("")) {
                txtUserName_nav.setText("ورود");
            } else {
                txtUserName_nav.setText(username);
            }
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(intent, 0);
            finish();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void disableScrollingListViewInNav() {

        settingListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return motionEvent.getAction() == MotionEvent.ACTION_MOVE; // Indicates that this has been handled by you and will not be forwarded further.
            }
        });
        productListView_nav.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return motionEvent.getAction() == MotionEvent.ACTION_MOVE; // Indicates that this has been handled by you and will not be forwarded further.
            }
        });
        BuyMenuListView_nav.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return motionEvent.getAction() == MotionEvent.ACTION_MOVE; // Indicates that this has been handled by you and will not be forwarded further.
            }
        });


    }

    private void showMoreInfoInNav() {
        linearInfo_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linearMoreInfo_main.getVisibility() == View.GONE) {
                    imgArrowInfo_nav.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_48);
                    linearMoreInfo_main.setVisibility(View.VISIBLE);


                } else {
                    linearMoreInfo_main.setVisibility(View.GONE);
                    imgArrowInfo_nav.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_48);
                }
            }
        });
    }

    private void openDrawer() {
        btnMenu_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }


    private void addNavMenuList() {
        buyMenuAdapterArrayList = new ArrayList<>();
        buyMenuAdapterArrayList.add(new buyMenuListItem(R.drawable.ic_baseline_favorite_24_color_primary, "علاقه مندی ها"));
        buyMenuAdapterArrayList.add(new buyMenuListItem(R.drawable.ic_baseline_list_48, "لیست دسته بندی"));
        BuyMenuListView_nav.setAdapter(new buyMenuAdapter(MainActivity.this, R.layout.buy_menu_list, buyMenuAdapterArrayList));


        productItems = new ArrayList<>();
        productItems.add(new productMenuListItem("پشنهاد ویژه ی تخفیفا"));
        productItems.add(new productMenuListItem("بیشترین تخفیف ها"));
        productItems.add(new productMenuListItem("پربازدیدترین ها"));
        productItems.add(new productMenuListItem("جدیدترین ها"));
        productItems.add(new productMenuListItem("نزدیک ترین ها"));
        productListView_nav.setAdapter(new productMenuAdaapter(MainActivity.this, R.layout.product_menu_list, productItems));


        settingItems = new ArrayList<>();
        settingItems.add(new settingMenuListItem("تنظیمات"));
        settingItems.add(new settingMenuListItem("سوالات متداول"));
        settingItems.add(new settingMenuListItem("درباره ما"));
        settingListView.setAdapter(new settingMenuAdapter(MainActivity.this, R.layout.setting_menu_list, settingItems));


    }



    private void showCustomDialogForExitProfile() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_warning_exit_from_profile);
        dialog.setCancelable(true);
       // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        (dialog.findViewById(R.id.bt_not_close_dialog_exit_from_profile)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        (dialog.findViewById(R.id.bt_close_dialog_exit_from_profile)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", "ورود");
                editor.commit();
                drawerLayout.closeDrawer(Gravity.RIGHT);

                preManeger.setStartSlider(true);

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }




    private void setupSlider() {

        layout_dots_image_slider_main = (LinearLayout) findViewById(R.id.layout_dots_image_slider_main);
        viewPager_image_slider_main = (ViewPager) findViewById(R.id.viewPager_image_slider_main);
        adapterImageSliderMain = new AdapterImageSliderMain(this, new ArrayList<Image_Slider_Main>());

        final List<Image_Slider_Main> items = new ArrayList<>();
        for (int i = 0; i < array_image_image_slider_main.length; i++) {
            Image_Slider_Main obj = new Image_Slider_Main();
            obj.image = array_image_image_slider_main[i];
            items.add(obj);

        }

        adapterImageSliderMain.setItems(items);
        viewPager_image_slider_main.setAdapter(adapterImageSliderMain);

        // displaying selected image first
        viewPager_image_slider_main.setCurrentItem(0);
        addBottomDots(layout_dots_image_slider_main, adapterImageSliderMain.getCount(), 0);
        viewPager_image_slider_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                addBottomDots(layout_dots_image_slider_main, adapterImageSliderMain.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        startAutoSlider(adapterImageSliderMain.getCount());

    }

    public static class AdapterImageSliderMain extends PagerAdapter {

        private Activity act;
        private List<Image_Slider_Main> items2;

        private AdapterImageSliderMain.OnItemClickListener onItemClickListener;

        public interface OnItemClickListener {
            void onItemClick(View view, Image obj);
        }

        public void setOnItemClickListener(AdapterImageSliderMain.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        // constructor
        private AdapterImageSliderMain(Activity activity, List<Image_Slider_Main> items) {
            this.act = activity;
            this.items2 = items;
        }

        @Override
        public int getCount() {
            return this.items2.size();
        }

        public void setItems(List<Image_Slider_Main> items) {
            this.items2 = items;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final Image_Slider_Main o2 = items2.get(position);
            Log.i("TAG", "instantiateItem: "+o2);
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_image_slider_main, container, false);

            ImageView image =  v.findViewById(R.id.image_from_item_image_slider_main);
            displayImageFromUrl(act, image, o2.image);
            Log.i("TAG", "instantiateItem: "+o2.image);

            ((ViewPager) container).addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);

        }

    }






    public static void displayImageFromUrl(Context ctx, ImageView img, String drawable) {

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(ctx);
        circularProgressDrawable.setStrokeWidth(15f);
        circularProgressDrawable.setCenterRadius(75f);
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(C.context, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        circularProgressDrawable.start();

        try {
            Glide.with(ctx).load(drawable)
                    .placeholder(circularProgressDrawable)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img);
        } catch (Exception e) {
            Log.e("TAG", "displayImageFromUrl: "+e.toString());
        }
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 25;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle_outline_image_slider_main);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setImageResource(R.drawable.shape_circle_image_slider_main);
        }
    }

    private void startAutoSlider(final int count) {
        runnable_image_slider_main = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager_image_slider_main.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager_image_slider_main.setCurrentItem(pos);
                handler_image_slider_main.postDelayed(runnable_image_slider_main, 3000);
            }
        };
        handler_image_slider_main.postDelayed(runnable_image_slider_main, 3000);
    }


    @Override
    public void onDestroy() {
        if (runnable_image_slider_main != null) handler_image_slider_main.removeCallbacks(runnable_image_slider_main);
        super.onDestroy();
    }


}