package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.takhfif01.R;
import com.example.takhfif01.adapter.buyMenuAdapter;
import com.example.takhfif01.adapter.profileMenuAdapter;
import com.example.takhfif01.model.buyMenuListItem;
import com.example.takhfif01.model.profileListItem;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    ArrayList<profileListItem> profileListItems;

    ListView profileMenuListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        addMenuList();


    }

    private void init() {
        profileMenuListView = findViewById(R.id.profileMenuListView);

    }

    private void addMenuList() {
        profileListItems = new ArrayList<>();
        profileListItems.add(new profileListItem(R.drawable.ic_baseline_account_balance_wallet_24_color_primary, "افزایش اعتبار"));
        profileListItems.add(new profileListItem(R.drawable.ic_baseline_favorite_24_color_primary, "علاقه مندی ها"));
        profileListItems.add(new profileListItem(R.drawable.ic_baseline_comment_24_primary_color, "نقد و نظرات"));
        profileListItems.add(new profileListItem(R.drawable.ic_baseline_person_24_primary_color, "تغییر اطلاعات حساب"));
        profileMenuListView.setAdapter(new profileMenuAdapter(ProfileActivity.this, R.layout.profile_menu_list, profileListItems));


    }
}