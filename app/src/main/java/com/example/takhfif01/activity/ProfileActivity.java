package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.takhfif01.R;
import com.example.takhfif01.activity.introSlider.SliderPreManger;
import com.example.takhfif01.adapter.buyMenuAdapter;
import com.example.takhfif01.adapter.profileEditMenuAdapter;
import com.example.takhfif01.adapter.profileMenuAdapter;
import com.example.takhfif01.context.C;
import com.example.takhfif01.model.buyMenuListItem;
import com.example.takhfif01.model.profileEditListItem;
import com.example.takhfif01.model.profileListItem;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    ArrayList<profileListItem> profileListItems;
    ArrayList<profileEditListItem> profileEditListItems;

    ListView profileMenuListView, profileEditMenuListView;

    TextView txtUserName_profile;

    LinearLayout linear_exitProfile;

    public static SharedPreferences preferences;

    private SliderPreManger preManeger;

    String username, phoneNumber,email,accNumber;

    ImageView btnBackProfile;

    SwipeRefreshLayout swipe_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        setUserNameTopOfProfile();
        addMenuList();
        exitProfile();
        backProfile();
        refreshBySwipe();


    }


    private void init() {
        preManeger = new SliderPreManger(C.context);
        profileMenuListView = findViewById(R.id.profileMenuListView);
        profileEditMenuListView = findViewById(R.id.profileEditMenuListView);
        txtUserName_profile = findViewById(R.id.txtUserName_profile);
        linear_exitProfile = findViewById(R.id.linear_exitProfile);
        btnBackProfile = findViewById(R.id.btnBackProfile);
        swipe_profile = findViewById(R.id.swipe_profile);

    }

    private void refreshBySwipe() {

        swipe_profile.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                setUserNameTopOfProfile();
                addMenuList();

                swipe_profile.setRefreshing(false);
            }
        });

    }

    private void backProfile() {
        btnBackProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void exitProfile() {
        linear_exitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialogForExitProfile();
            }
        });
    }

    private void setUserNameTopOfProfile() {

        preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
        username = preferences.getString("username", "");
        phoneNumber = preferences.getString("phoneNumber", "");
        email = preferences.getString("email", "");
        accNumber = preferences.getString("accNumber", "");

        txtUserName_profile.setText(username);
        Log.i("TAG", "setUserNameTopOfProfile: username" + username);

        if (username != null || phoneNumber != null) {
            if (username.equals("") || phoneNumber.equals("")) {
                finish();
            } else {
                txtUserName_profile.setText(username);
            }
        } else {
            finish();
        }
    }

    private void addMenuList() {
        profileListItems = new ArrayList<>();
        profileListItems.add(new profileListItem(R.drawable.ic_baseline_favorite_24_color_primary, "علاقه مندی ها"));
        profileListItems.add(new profileListItem(R.drawable.ic_baseline_comment_24_primary_color, "نقد و نظرات"));
        profileListItems.add(new profileListItem(R.drawable.ic_baseline_delete_forever_24_color_primary, "غیرفعال سازی حساب کاربری"));
        profileMenuListView.setAdapter(new profileMenuAdapter(ProfileActivity.this, R.layout.profile_menu_list, profileListItems));

        profileEditListItems = new ArrayList<>();
        profileEditListItems.add(new profileEditListItem(R.drawable.ic_baseline_person_24_primary_color, "نام و نام خانوادگی", username + ""));
        profileEditListItems.add(new profileEditListItem(R.drawable.ic_mobile_baseline_stay_primary_portrait_24_color_primary, "شماره تلفن همراه", phoneNumber + ""));
        profileEditListItems.add(new profileEditListItem(R.drawable.ic_baseline_alternate_email_24_color_primary, "پست الکترونیک", email + ""));
        profileEditListItems.add(new profileEditListItem(R.drawable.ic_baseline_credit_card_24_color_primary, "شماره کارت جهت بازگردانی وجه نقد", accNumber + ""));
        profileEditMenuListView.setAdapter(new profileEditMenuAdapter(ProfileActivity.this, R.layout.profile_edit_menu_list, profileEditListItems));


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
            }
        });

        (dialog.findViewById(R.id.bt_close_dialog_exit_from_profile)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                //editor.putString("username", "");
                editor.clear();
                editor.commit();

                preManeger.setStartSlider(true);

                Intent intent = new Intent(C.context, LoginActivity.class);
                intent.putExtra("username","");
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


}