package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import androidx.transition.TransitionManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.takhfif01.R;
import com.example.takhfif01.context.C;
import com.example.takhfif01.model.Image_Slider_Main;
import com.example.takhfif01.utils.ViewAnimation;

import java.util.ArrayList;
import java.util.List;

public class ShowShopActivity extends AppCompatActivity {

    LinearLayout linearLayoutAdressName,
            linearLayoutMoreInfoName_showShop,
            linearLayoutReadMoreName_showShop;
    View linearLayoutAdressDetail,
            linearLayoutMoreInfoDetail_showShop,
            linearLayoutReadMoreDetail_showShop;
    CardView cardViewAddress_showShop, cardViewMoreInfo_showShop, cardViewReadMore_showShop;
    ImageView imageViewAddressArrow_showShop, imageViewMoreInfoArrow_showShop, imageViewReadMoreArrow_showShop;


    private int width_phone, height_phone, width_height;


    private ViewPager viewPager_image_slider_showShop;
    private LinearLayout layout_dots_image_slider_showShop;
    private AdapterImageSliderShowShop adapterImageSliderShowShop;
    private Runnable runnable_image_slider_showShop = null;
    private Handler handler_image_slider_showShop = new Handler();

    private static String[] array_image_image_slider_showShop = {
            "https://png.pngtree.com/thumb_back/fw800/back_our/20190622/ourmid/pngtree-gorgeous-technology-dot-line-structure-banner-background-image_210889.jpg",
            "https://png.pngtree.com/thumb_back/fw800/back_our/20190622/ourmid/pngtree-gorgeous-technology-dot-line-structure-banner-background-image_210889.jpg",
            "https://png.pngtree.com/thumb_back/fw800/back_our/20190622/ourmid/pngtree-gorgeous-technology-dot-line-structure-banner-background-image_210889.jpg",
            "https://png.pngtree.com/thumb_back/fw800/back_our/20190622/ourmid/pngtree-gorgeous-technology-dot-line-structure-banner-background-image_210889.jpg",
    };


    private NestedScrollView nested_scroll_view;
    private ImageButton bt_toggle_text, bt_toggle_input;
    private Button bt_hide_text, bt_save_input, bt_hide_input;
    private View lyt_expand_text, lyt_expand_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shop);
        init();
        setupSlider();
        showAddress();
        showMoreInfo();
        showReadMore();

    }


    private void init() {
        linearLayoutAdressName = findViewById(R.id.linearLayoutAddressName_showShop);
        linearLayoutAdressDetail = findViewById(R.id.linearLayoutAddressDetail_showShop);
        cardViewAddress_showShop = findViewById(R.id.cardViewAddress_showShop);
        imageViewAddressArrow_showShop = findViewById(R.id.imageViewAddressArrow_showShop);
        cardViewMoreInfo_showShop = findViewById(R.id.cardViewMoreInfo_showShop);
        linearLayoutMoreInfoDetail_showShop = findViewById(R.id.linearLayoutMoreInfoDetail_showShop);
        linearLayoutMoreInfoName_showShop = findViewById(R.id.linearLayoutMoreInfoName_showShop);
        imageViewMoreInfoArrow_showShop = findViewById(R.id.imageViewMoreInfoArrow_showShop);
        cardViewReadMore_showShop = findViewById(R.id.cardViewReadfMore_showShop);
        linearLayoutReadMoreDetail_showShop = findViewById(R.id.linearLayoutReadfMoreDetail_showShop);
        linearLayoutReadMoreName_showShop = findViewById(R.id.linearLayoutReadfMoreName_showShop);
        imageViewReadMoreArrow_showShop = findViewById(R.id.imageViewReadfMoreArrow_showShop);
        nested_scroll_view = findViewById(R.id.nested_scroll_view_show_shop);
    }


    private void showReadMore() {
/*
        linearLayoutReadMoreName_showShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (linearLayoutReadMoreDetail_showShop.getVisibility()== View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewReadMore_showShop);
                    linearLayoutReadMoreDetail_showShop.setVisibility(View.VISIBLE);
                    imageViewReadMoreArrow_showShop.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24_black);
                }else {
                    TransitionManager.endTransitions(cardViewReadMore_showShop);
                    linearLayoutReadMoreDetail_showShop.setVisibility(View.GONE);
                    imageViewReadMoreArrow_showShop.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24_black);
                }
            }
        });*/


        linearLayoutReadMoreName_showShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleReadMoreText(imageViewReadMoreArrow_showShop);

            }
        });

    }


    private void showMoreInfo() {
/*
        linearLayoutMoreInfoName_showShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (linearLayoutMoreInfoDetail_showShop.getVisibility()== View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewMoreInfo_showShop);
                    linearLayoutMoreInfoDetail_showShop.setVisibility(View.VISIBLE);
                    imageViewMoreInfoArrow_showShop.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24_black);
                }else {
                    TransitionManager.endTransitions(cardViewMoreInfo_showShop);
                    linearLayoutMoreInfoDetail_showShop.setVisibility(View.GONE);
                    imageViewMoreInfoArrow_showShop.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24_black);
                }
            }
        });*/
        linearLayoutMoreInfoName_showShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleDetailText(imageViewMoreInfoArrow_showShop);

            }
        });

    }

    private void showAddress() {
/*
        linearLayoutAdressName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (linearLayoutAdressDetail.getVisibility()==View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewAddress_showShop);
                    linearLayoutAdressDetail.setVisibility(View.VISIBLE);
                    imageViewAddressArrow_showShop.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24_black);
                }else {
                    TransitionManager.endTransitions(cardViewAddress_showShop);
                    linearLayoutAdressDetail.setVisibility(View.GONE);
                    imageViewAddressArrow_showShop.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24_black);
                }
            }
        });
*/

        linearLayoutAdressName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleAddressText(imageViewAddressArrow_showShop);

            }
        });
    }

    private void toggleAddressText(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(linearLayoutAdressDetail, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    nestedScrollTo(nested_scroll_view, linearLayoutAdressDetail);
                }
            });
        } else {
            ViewAnimation.collapse(linearLayoutAdressDetail);
        }

    }

    private void toggleDetailText(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(linearLayoutMoreInfoDetail_showShop, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    nestedScrollTo(nested_scroll_view, linearLayoutMoreInfoDetail_showShop);
                }
            });
        } else {
            ViewAnimation.collapse(linearLayoutMoreInfoDetail_showShop);
        }

    }

    private void toggleReadMoreText(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(linearLayoutReadMoreDetail_showShop, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    nestedScrollTo(nested_scroll_view, linearLayoutReadMoreDetail_showShop);
                }
            });
        } else {
            ViewAnimation.collapse(linearLayoutReadMoreDetail_showShop);
        }

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

    public static void nestedScrollTo(final NestedScrollView nested, final View targetView) {
        nested.post(new Runnable() {
            @Override
            public void run() {
                nested.scrollTo(500, targetView.getBottom());
            }
        });
    }


    private void setupSlider() {

        layout_dots_image_slider_showShop = (LinearLayout) findViewById(R.id.layout_dots_image_slider_show_shop);
        viewPager_image_slider_showShop = (ViewPager) findViewById(R.id.viewPager_image_slider_show_shop);
        adapterImageSliderShowShop = new AdapterImageSliderShowShop(ShowShopActivity.this, new ArrayList<Image_Slider_Main>());

        final List<Image_Slider_Main> items = new ArrayList<>();
        for (int i = 0; i < array_image_image_slider_showShop.length; i++) {
            Image_Slider_Main obj = new Image_Slider_Main();
            obj.image = array_image_image_slider_showShop[i];
            items.add(obj);

        }

        adapterImageSliderShowShop.setItems(items);
        viewPager_image_slider_showShop.setAdapter(adapterImageSliderShowShop);

        // displaying selected image first
        viewPager_image_slider_showShop.setCurrentItem(0);
        addBottomDots(layout_dots_image_slider_showShop, adapterImageSliderShowShop.getCount(), 0);
        viewPager_image_slider_showShop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                addBottomDots(layout_dots_image_slider_showShop, adapterImageSliderShowShop.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        startAutoSlider(adapterImageSliderShowShop.getCount());

    }

    public static class AdapterImageSliderShowShop extends PagerAdapter {

        private Activity act;
        private List<Image_Slider_Main> items2;

        private MainActivity.AdapterImageSliderMain.OnItemClickListener onItemClickListener;

        public interface OnItemClickListener {
            void onItemClick(View view, Image obj);
        }

        public void setOnItemClickListener(MainActivity.AdapterImageSliderMain.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        // constructor
        private AdapterImageSliderShowShop(Activity activity, List<Image_Slider_Main> items) {
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
            Log.i("TAG", "instantiateItem: " + o2);
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_image_slider_main, container, false);

            ImageView image = v.findViewById(R.id.image_from_item_image_slider_main);
            displayImageFromUrl(act, image, o2.image);
            Log.i("TAG", "instantiateItem: " + o2.image);

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
            Log.e("TAG", "displayImageFromUrl: " + e.toString());
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
        runnable_image_slider_showShop = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager_image_slider_showShop.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager_image_slider_showShop.setCurrentItem(pos);
                handler_image_slider_showShop.postDelayed(runnable_image_slider_showShop, 3000);
            }
        };
        handler_image_slider_showShop.postDelayed(runnable_image_slider_showShop, 3000);
    }

    private void getSizeScreen() {

        Display screensize = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        screensize.getSize(size);
        width_phone = size.x;
        height_phone = size.y;


        if (width_phone <= 480 && height_phone <= 800) {
            width_height = 13;

        } else if (width_phone <= 720 && height_phone <= 1184) {
            width_height = 20;
        } else {

            width_height = 25;
        }


        Log.i("TAG", "getSizeScreen: \n width=" + width_phone + "\n" + "height=" + height_phone);


    }

    @Override
    public void onDestroy() {
        if (runnable_image_slider_showShop != null)
            handler_image_slider_showShop.removeCallbacks(runnable_image_slider_showShop);
        super.onDestroy();
    }


}