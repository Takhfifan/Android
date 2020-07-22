package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.takhfif01.R;
import com.example.takhfif01.activity.introSlider.SliderPreManger;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    Button btn_login_getKey, btn_login_send;
    RelativeLayout scroll_login_getKey, scroll_login_setKey;
    EditText name_signup, lastname_signup, number_signup,
            number_getcode,
            showNumber_getcode;
    TextInputLayout name_layout_signup, lastname_layout_signup, number_layout_signup,
            number_layout_getcode;
    ImageView backbtn_getcode;

    SliderPreManger preManeger;

    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        preManeger = new SliderPreManger(getApplicationContext());

        backToGetKeyNumber();
        getKeyNumber();
        setKeyNumber();


    }

    private void backToGetKeyNumber() {
        backbtn_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroll_login_getKey.setVisibility(View.VISIBLE);
                scroll_login_setKey.setVisibility(View.GONE);
            }
        });
    }

    private void setKeyNumber() {

        btn_login_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = name_signup.getText().toString().trim() + " " + lastname_signup.getText().toString().trim();
                String name = name_signup.getText().toString().trim();
                String lastName = lastname_signup.getText().toString().trim();
                String phoneNumber = number_signup.getText().toString().trim();
                Log.i("TAG", "onClick username: " + username);


                if (checkCode()) {
                    preManeger.setStartSlider(false);

                    preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", username);
                    editor.putString("name", name);
                    editor.putString("lastName", lastName);
                    editor.putString("phoneNumber", phoneNumber);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //intent.putExtra("username", username);
                    //setResult(RESULT_OK, intent);

                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean checkCode() {
        if (number_getcode.getText().toString().trim().isEmpty() || number_getcode.getText().toString().trim().length() < 4) {
            number_layout_getcode.setErrorEnabled(true);
            number_layout_getcode.setError("لطفا کد 4رقمی را درست وارد کنید!");
            return false;
        }
        return true;
    }

    private void getKeyNumber() {
        btn_login_getKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (checkFieldName()) {
                    if (checkFieldLastName()) {
                        if (checkFieldNumber()) {
                            String number = number_signup.getText().toString().trim();
                            showNumber_getcode.setText(number);

                            scroll_login_getKey.setVisibility(View.GONE);
                            scroll_login_setKey.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
    }

    private boolean checkFieldNumber() {
        if (!number_signup.getText().toString().startsWith("09") ||

                number_signup.getText().toString().length() != 11) {
            number_layout_signup.setErrorEnabled(true);
            number_layout_signup.setError("شماره ی خود را درست وارد کنید\nمثلا:091234567891");
            return false;
        }
        return true;
    }

    private boolean checkFieldLastName() {
        if (!(lastname_signup.getText().toString().trim().length() >= 4)) {
            lastname_layout_signup.setErrorEnabled(true);
            lastname_layout_signup.setError("نام خانوادگی باید حداقل 4 حرف باشد");
            return false;
        }
        return true;
    }

    private boolean checkFieldName() {
        if (!(name_signup.getText().toString().trim().length() >= 3)) {
            name_layout_signup.setErrorEnabled(true);
            name_layout_signup.setError("نام باید حداقل 3 حرف باشد");
            return false;
        }
        return true;
    }

    private void init() {
        btn_login_getKey = findViewById(R.id.btn_login_getKey);
        btn_login_send = findViewById(R.id.btn_login_send);
        scroll_login_getKey = findViewById(R.id.scroll_login_getKey);
        scroll_login_setKey = findViewById(R.id.scroll_login_setKey);
        name_signup = findViewById(R.id.name_signup);
        lastname_signup = findViewById(R.id.lastname_signup);
        number_signup = findViewById(R.id.number_signup);
        backbtn_getcode = findViewById(R.id.backbtn_getcode);
        number_getcode = findViewById(R.id.number_getcode);
        name_layout_signup = findViewById(R.id.name_layout_signup);
        lastname_layout_signup = findViewById(R.id.lastname_layout_signup);
        number_layout_signup = findViewById(R.id.number_layout_signup);
        showNumber_getcode = findViewById(R.id.showNumber_getcode);
        number_layout_getcode = findViewById(R.id.number_layout_getcode);
    }
}