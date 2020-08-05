package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.takhfif01.R;

public class CommentActivity extends AppCompatActivity {

    CardView cardView_goto_set_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
        gotoSetCommentActivity();
    }


    private void init() {
        cardView_goto_set_comment = findViewById(R.id.cardView_goto_set_comment);
    }
    private void gotoSetCommentActivity() {
        cardView_goto_set_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommentActivity.this, SetCommentActivity.class);
                startActivity(intent);
            }
        });
    }
}