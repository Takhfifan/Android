package com.example.takhfif01.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
import com.example.takhfif01.utils.ViewAnimation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private final int CAMERA_REQUEST_CODE = 100;
    public static int PROFILE_REQUEST_CODE = 200;

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

    private CardView btn_goto_profile_main,gotoShowShop,goToAllShopList,goToMyWallet;

    private ImageView btnSearchqr_main;


    NestedScrollView nested_scroll_view;


    private int width_phone,height_phone,width_height;



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



   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PROFILE_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String username = bundle.getString("username");
            txtUserName_nav.setText(username);

            Log.i("TAG", "onActivityResult: username" + username);

            preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", username);
            editor.commit();
            checkUsernameInNav();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CAMERA_REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "مجوز تایید شد شما میتوانید وارد شوید", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "مجوز رد شد", Toast.LENGTH_SHORT).show();

            }

        }

    }


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
        //showMoreInfoInNav();
        goToProfile();
        goToShowShop();
        goToListOfAllShopActivity();
        goToCameraForSearch();
        gtoMyWalletActivity();



        linearInfo_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionText(imgArrowInfo_nav);
            }
        });


    }



    private void toggleSectionText(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(linearMoreInfo_main, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    nestedScrollTo(nested_scroll_view, linearMoreInfo_main);
                }
            });
        } else {
            ViewAnimation.collapse(linearMoreInfo_main);
        }
    }


    public static void nestedScrollTo(final NestedScrollView nested, final View targetView) {
        nested.post(new Runnable() {
            @Override
            public void run() {
                nested.scrollTo(500, targetView.getBottom());
            }
        });
    }


    public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }





    private void init() {
        btnMenu_main = findViewById(R.id.btnMenu_main);
        drawerLayout = findViewById(R.id.drawer_main);
        BuyMenuListView_nav = findViewById(R.id.BuyMenuListView_nav);
        linearInfo_main = findViewById(R.id.linearInfo_main);
        linearMoreInfo_main = findViewById(R.id.linearMoreInfo_main);
        imgArrowInfo_nav = findViewById(R.id.imgArrowInfo_nav);
        //imgArrowInfo_nav.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_48);
        productListView_nav = findViewById(R.id.productListView_nav);
        settingListView = findViewById(R.id.settingListView);
        txtUserName_nav = findViewById(R.id.txtUserName_nav);
        txtexit_nav = findViewById(R.id.txtexit_nav);
        preManeger = new SliderPreManger(C.context);
        btn_goto_profile_main = findViewById(R.id.btn_goto_profile_main);
        gotoShowShop = findViewById(R.id.gotoShowShop);
        goToAllShopList = findViewById(R.id.goToAllShopList);
        btnSearchqr_main = findViewById(R.id.btnSearchqr_main);
        goToMyWallet = findViewById(R.id.goToMyWallet);
        nested_scroll_view = findViewById(R.id.nested_scroll_view);


    }

    private void gtoMyWalletActivity() {
        goToMyWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyWalletActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToCameraForSearch() {
        btnSearchqr_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    requestCameraPermission();

                } else {

                    Toast.makeText(MainActivity.this, "مجوز قبلا دریافت شده", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)) {

            showCustomDialogForShowReqCamera();

     /*       new AlertDialog.Builder(this)
                    .setTitle("درخواست مجوز")
                    .setMessage("برای دسترسی به دوربین باید مجوز را تایید کنید")
                    .setPositiveButton("موافقم", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            reqPermission();

                        }
                    })
                    .setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();

                        }
                    })
                    .create()
                    .show();*/

        } else {

            reqPermission();

        }
    }

    private void showCustomDialogForShowReqCamera() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.alert_dialog_show_info_req_camera);
        dialog.setCancelable(true);
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        (dialog.findViewById(R.id.bt_cancel_alert_dialog_show_info_req_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        (dialog.findViewById(R.id.bt_agree_alert_dialog_show_info_req_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reqPermission();
                dialog.dismiss();

            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void reqPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
    }

    private void goToListOfAllShopActivity() {
        goToAllShopList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListOfAllShopActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToShowShop() {
        gotoShowShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShowShopActivity.class);
                startActivity(intent);
            }
        });
    }


    private void goToProfile() {
        btn_goto_profile_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                //startActivity(intent);
                startActivityForResult(intent,PROFILE_REQUEST_CODE);
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
        if (txtUserName_nav.getText().toString().equals("")) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            //startActivityForResult(intent, 0);
            startActivity(intent);
            finish();
            preManeger.setStartSlider(true);
        }
    }

    private void setUpUsernameInNav() {
        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = preferences.getString("username", "");

        txtUserName_nav.setText(username);
        Log.i("TAG", "setUpUsernameInNav: username" + username);

        if (username != null) {
            if (username.equals("")) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                //startActivityForResult(intent, 0);
                startActivity(intent);
                finish();
            } else {
                txtUserName_nav.setText(username);
            }
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            //startActivityForResult(intent, 0);
            startActivity(intent);
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
        BuyMenuListView_nav.setAdapter(new buyMenuAdapter(MainActivity.this, R.layout.menu_list_buy, buyMenuAdapterArrayList));


        productItems = new ArrayList<>();
        productItems.add(new productMenuListItem("پشنهاد ویژه ی تخفیفا"));
        productItems.add(new productMenuListItem("بیشترین تخفیف ها"));
        productItems.add(new productMenuListItem("پربازدیدترین ها"));
        productItems.add(new productMenuListItem("جدیدترین ها"));
        productListView_nav.setAdapter(new productMenuAdaapter(MainActivity.this, R.layout.menu_list_product, productItems));


        settingItems = new ArrayList<>();
        settingItems.add(new settingMenuListItem("سوالات متداول"));
        settingItems.add(new settingMenuListItem("گزارش خطا"));
        settingItems.add(new settingMenuListItem("درباره ما"));
        settingListView.setAdapter(new settingMenuAdapter(MainActivity.this, R.layout.menu_list_setting, settingItems));


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
/**
 *
 * BE CAREFUL MAYBE THIS GONNA MAKE SOME PROBLEM
 *
 * editor.putString("username", "");
 *                 editor.clear();
 *                 editor.commit();
 *
 */
        (dialog.findViewById(R.id.bt_close_dialog_exit_from_profile)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                //editor.putString("username", "");
                editor.clear();
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
            //int width_height = 25;
            getSizeScreen();
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

    private void getSizeScreen(){

        Display screensize = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        screensize.getSize(size);
        width_phone  = size.x;
        height_phone = size.y;



        if (width_phone<=480 && height_phone<=800){
            width_height = 13;

        }else if (width_phone<=720 && height_phone<=1184){
            width_height = 20;
        }
        else {

            width_height = 25;
        }


        Log.i("TAG", "getSizeScreen: \n width="+width_phone+"\n"+"height="+height_phone);


    }


    @Override
    public void onDestroy() {
        if (runnable_image_slider_main != null) handler_image_slider_main.removeCallbacks(runnable_image_slider_main);
        super.onDestroy();
    }


}