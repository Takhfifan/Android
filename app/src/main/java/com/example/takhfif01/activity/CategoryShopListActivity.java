package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.takhfif01.R;

public class CategoryShopListActivity extends AppCompatActivity {

    ImageView show_alert_dialog_sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_shop_list);
        init();
    }

    private void init() {
        show_alert_dialog_sort = findViewById(R.id.show_alert_dialog_sort);
    }
}