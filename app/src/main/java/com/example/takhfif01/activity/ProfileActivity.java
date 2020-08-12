package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takhfif01.R;
import com.example.takhfif01.activity.introSlider.SliderPreManger;
import com.example.takhfif01.adapter.profileEditMenuAdapter;
import com.example.takhfif01.adapter.profileMenuAdapter;
import com.example.takhfif01.context.C;
import com.example.takhfif01.model.profileEditListItem;
import com.example.takhfif01.model.profileListItem;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {




    ArrayList<profileListItem> profileListItems;
    ArrayList<profileEditListItem> profileEditListItems;

    ListView profileMenuListView/*, profileEditMenuListView*/;

    TextView txtUserName_profile, txtDescProfileEditName, txtDescProfileEditPhoneNumber, txtDescProfileEditEmail, txtDescProfileEditCreditCard;

    LinearLayout linear_exitProfile, linearProfileEditName, linearProfileEditPhoneNumber, linearProfileEditEmail, linearProfileCreditCard;

    public static SharedPreferences preferences;

    private SliderPreManger preManeger;

    String username, phoneNumber, email, accNumber;

    ImageView btnBackProfile;

    SwipeRefreshLayout swipe_profile;

    @Override
    public void onBackPressed() {

        preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
        username = preferences.getString("username", "");

        Intent intent = new Intent(C.context, MainActivity.class);
        intent.putExtra("username", username);
        setResult(RESULT_OK, intent);


        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        setUserNameTopOfProfile();
        addMenuList();
        cardViewShowInformation();
        cardViewEditInformation();
        exitProfile();
        backProfile();
        refreshBySwipe();


    }


    private void init() {
        preManeger = new SliderPreManger(C.context);
        profileMenuListView = findViewById(R.id.profileMenuListView);
        //profileEditMenuListView = findViewById(R.id.profileEditMenuListView);
        txtUserName_profile = findViewById(R.id.txtUserName_profile);
        linear_exitProfile = findViewById(R.id.linear_exitProfile);
        btnBackProfile = findViewById(R.id.btnBackProfile);
        swipe_profile = findViewById(R.id.swipe_profile);
        txtDescProfileEditName = findViewById(R.id.txtDescProfileEditName);
        txtDescProfileEditPhoneNumber = findViewById(R.id.txtDescProfileEditPhoneNumber);
        txtDescProfileEditEmail = findViewById(R.id.txtDescProfileEditEmail);
        txtDescProfileEditCreditCard = findViewById(R.id.txtDescProfileEditCreditCard);
        linearProfileEditName = findViewById(R.id.linearProfileEditName);
        linearProfileEditEmail = findViewById(R.id.linearProfileEditEmail);
        linearProfileEditPhoneNumber = findViewById(R.id.linearProfileEditPhoneNumber);
        linearProfileCreditCard = findViewById(R.id.linearProfileCreditCard);

    }

    private void refreshBySwipe() {

        swipe_profile.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                setUserNameTopOfProfile();
                //addMenuList();
                cardViewShowInformation();

                swipe_profile.setRefreshing(false);
            }
        });

    }

    private void backProfile() {
        btnBackProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
                username = preferences.getString("username", "");

                Intent intent = new Intent(C.context, MainActivity.class);
                intent.putExtra("username", username);
                setResult(RESULT_OK, intent);

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

    private void cardViewShowInformation() {
        txtDescProfileEditName.setText(username);
        txtDescProfileEditPhoneNumber.setText(phoneNumber);
        txtDescProfileEditEmail.setText(email);
        txtDescProfileEditCreditCard.setText(accNumber);

    }

    private void cardViewEditInformation() {

        linearProfileEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialogChaneUserName();
            }
        });

        linearProfileEditPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showBottomSheetDialogChanePhoneNumber();
            }
        });

        linearProfileEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showBottomSheetDialogAddEmail();
            }
        });

        linearProfileCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showBottomSheetDialogAddAccCard();
            }
        });

    }

    private void addMenuList() {
        profileListItems = new ArrayList<>();
        profileListItems.add(new profileListItem(R.drawable.ic_baseline_favorite_24_color_primary, "علاقه مندی ها"));
        profileListItems.add(new profileListItem(R.drawable.ic_baseline_comment_24_primary_color, "نقد و نظرات"));
        profileListItems.add(new profileListItem(R.drawable.ic_baseline_delete_forever_24_color_primary, "غیرفعال سازی حساب کاربری"));
        profileMenuListView.setAdapter(new profileMenuAdapter(ProfileActivity.this, R.layout.menu_list_profile, profileListItems));

       /* profileEditListItems = new ArrayList<>();
        profileEditListItems.add(new profileEditListItem(R.drawable.ic_baseline_person_24_primary_color, "نام و نام خانوادگی", username + ""));
        profileEditListItems.add(new profileEditListItem(R.drawable.ic_mobile_baseline_stay_primary_portrait_24_color_primary, "شماره تلفن همراه", phoneNumber + ""));
        profileEditListItems.add(new profileEditListItem(R.drawable.ic_baseline_alternate_email_24_color_primary, "پست الکترونیک", email + ""));
        profileEditListItems.add(new profileEditListItem(R.drawable.ic_baseline_credit_card_24_color_primary, "شماره کارت جهت بازگردانی وجه نقد", accNumber + ""));
        profileEditMenuListView.setAdapter(new profileEditMenuAdapter(ProfileActivity.this, R.layout.menu_list_profile_edit, profileEditListItems));
*/

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
                intent.putExtra("username", "");
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void showBottomSheetDialogChaneUserName() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ProfileActivity.this);
        View parent = getLayoutInflater().inflate(R.layout.bottm_sheet_change_username_profile, null);

        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
        String name = preferences.getString("name", "");
        String lastName = preferences.getString("lastName", "");
        Log.i("TAG", "showBottomSheetDialogChaneUserName: name" + name + "\n" + "lastname" + lastName);


        final EditText name_change_profile = parent.findViewById(R.id.name_change_profile);
        final EditText lastname_change_profile = parent.findViewById(R.id.lastname_change_profile);

        final LinearLayout error_name_change_profile = parent.findViewById(R.id.error_name_change_profile);
        final LinearLayout error_lastname_change_profile = parent.findViewById(R.id.error_lastname_change_profile);


        name_change_profile.setText(name);
        lastname_change_profile.setText(lastName);


        final Button btn_change_username = parent.findViewById(R.id.btn_change_username);
        btn_change_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt_name_change_profile = name_change_profile.getText().toString().trim();
                String txt_lastname_change_profile = lastname_change_profile.getText().toString().trim();
                String txt_username_change_profile = txt_name_change_profile + " " + txt_lastname_change_profile;
                Log.i("TAG", "onClick:txt_name_change_profile " + txt_name_change_profile + "\n"
                        + "txt_lastname_change_profile" + txt_lastname_change_profile +
                        "\n" + "txt_username_change_profile" + txt_username_change_profile);

                if (!(name_change_profile.getText().toString().trim().length() >= 3)) {
                    /*name_layout_change_profile.setErrorEnabled(true);
                    name_layout_change_profile.setError("نام باید حداقل 3 حرف باشد");*/
                    error_name_change_profile.setVisibility(View.VISIBLE);


                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v.vibrate(200);
                    }

                } else {

                    if (!(lastname_change_profile.getText().toString().trim().length() >= 4)) {
                        /*lastname_layout_change_profile.setErrorEnabled(true);
                        lastname_layout_change_profile.setError("نام خانوادگی باید حداقل 4 حرف باشد");*/
                        error_lastname_change_profile.setVisibility(View.VISIBLE);



                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            v.vibrate(200);
                        }

                    } else {
                        //SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("name", txt_name_change_profile);
                        editor.putString("lastName", txt_lastname_change_profile);
                        editor.putString("username", txt_username_change_profile);
                        editor.commit();

                        name_change_profile.setText(txt_name_change_profile);
                        lastname_change_profile.setText(txt_lastname_change_profile);

                        txtDescProfileEditName.setText(txt_username_change_profile);

                        txtUserName_profile.setText(txt_username_change_profile);


                        Toast.makeText(ProfileActivity.this, "باموفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();

                    }
                }


            }
        });


        bottomSheetDialog.setContentView(parent);
        /*BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());
        bottomSheetBehavior.setPeekHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics())
        );*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());


        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }


        bottomSheetDialog.show();

    }

    private void showBottomSheetDialogChanePhoneNumber() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ProfileActivity.this);
        View parent = getLayoutInflater().inflate(R.layout.bottm_sheet_change_phone_nmber_profile, null);

        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
        String number = preferences.getString("phoneNumber", "");
        Log.i("TAG", "showBottomSheetDialogChanePhoneNumber: number=" + number);

        final EditText number_change_profile = parent.findViewById(R.id.number_change_profile);
        final TextInputLayout number_layout_change_profile = parent.findViewById(R.id.number_layout_change_profile);

        number_change_profile.setText(number);
        number_change_profile.setEnabled(false);

        final Button btn_add_email = parent.findViewById(R.id.btn_chane_number_profile);
        btn_add_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(parent);
        /*BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());
        bottomSheetBehavior.setPeekHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics())
        );*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());


        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        bottomSheetDialog.show();

    }

    private void showBottomSheetDialogAddEmail() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ProfileActivity.this);
        View parent = getLayoutInflater().inflate(R.layout.bottm_sheet_add_email_profile, null);

        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
        String email = preferences.getString("email", "");
        Log.i("TAG", "showBottomSheetDialogAddEmail: email=" + email);

        final EditText email_add_profile = parent.findViewById(R.id.email_add_profile);
        final LinearLayout error_email_add_profile = parent.findViewById(R.id.error_email_add_profile);

        email_add_profile.setText(email);

        final Button btn_add_email = parent.findViewById(R.id.btn_add_email);
        btn_add_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String txt_email_add_profile = email_add_profile.getText().toString().trim();
                Log.i("TAG", "onClick:email_add_profile " + txt_email_add_profile);



                if (email_add_profile.getText().toString().trim().length() !=0){



                if (txt_email_add_profile.lastIndexOf("@") <= 0
                        || txt_email_add_profile.lastIndexOf(".") < txt_email_add_profile.lastIndexOf("@")
                        || txt_email_add_profile.split("@").length > 2) {


                    /*email_layout_add_profile.setErrorEnabled(true);
                    email_layout_add_profile.setError("پست الکترونیک خود را درست وارد کنید!\nمثلا:myEmail@Info.com");*/

                    error_email_add_profile.setVisibility(View.VISIBLE);

                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v.vibrate(200);
                    }

                } else {
                    //SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", txt_email_add_profile);
                    editor.commit();


                    txtDescProfileEditEmail.setText(txt_email_add_profile);

                    Toast.makeText(ProfileActivity.this, "باموفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();

                }
            }else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", txt_email_add_profile);
                    editor.commit();


                    txtDescProfileEditEmail.setText(txt_email_add_profile);

                    Toast.makeText(ProfileActivity.this, "باموفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                }

            }
        });


        bottomSheetDialog.setContentView(parent);
        /*BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());
        bottomSheetBehavior.setPeekHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics())
        );*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());


        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }


        bottomSheetDialog.show();

    }

    private void showBottomSheetDialogAddAccCard() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ProfileActivity.this);
        View parent = getLayoutInflater().inflate(R.layout.bottm_sheet_add_acc_number_profile, null);


        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
        String accNumber = preferences.getString("accNumber", "");
        Log.i("TAG", "showBottomSheetDialogAddAccCard: accNumber=" + accNumber);

        final EditText acc_card_add_profile = parent.findViewById(R.id.acc_card_add_profile);
        final LinearLayout error_acc_card_add_profile = parent.findViewById(R.id.error_acc_card_add_profile);

        acc_card_add_profile.setText(accNumber);

        final Button btn_add_acc_card_profile = parent.findViewById(R.id.btn_add_acc_card_profile);
        btn_add_acc_card_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String txt_acc_card_add_profile = acc_card_add_profile.getText().toString().trim();
                Log.i("TAG", "onClick:acc_card_add_profile " + txt_acc_card_add_profile);


                switch (acc_card_add_profile.getText().toString().trim().length()) {
                    case 0:

                        //SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("accNumber", txt_acc_card_add_profile);
                        editor.commit();

                        txtDescProfileEditCreditCard.setText(txt_acc_card_add_profile);


                        Toast.makeText(ProfileActivity.this, "باموفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();


                        break;
                    case 16:

                        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
                        SharedPreferences.Editor editor2 = preferences2.edit();
                        editor2.putString("accNumber", txt_acc_card_add_profile);
                        editor2.commit();

                        txtDescProfileEditCreditCard.setText(txt_acc_card_add_profile);


                        Toast.makeText(ProfileActivity.this, "باموفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();


                        break;

                    default:
                        /*acc_card_layout_add_profile.setErrorEnabled(true);
                        acc_card_layout_add_profile.setError("نام باید 16 رقم باشد");*/


                        error_acc_card_add_profile.setVisibility(View.VISIBLE);

                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            v.vibrate(200);
                        }

                        break;
                }

/*
                if (!(acc_card_add_profile.getText().toString().trim().length() >= 16)) {
                    acc_card_layout_add_profile.setErrorEnabled(true);
                    acc_card_layout_add_profile.setError("نام باید 16 رقم باشد");


                } else {
                    //SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("accNumber", txt_acc_card_add_profile);
                    editor.commit();

                    txtDescProfileEditCreditCard.setText(txt_acc_card_add_profile);


                    Toast.makeText(ProfileActivity.this, "باموفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();

                }

*/
            }
        });


        bottomSheetDialog.setContentView(parent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());
        /*bottomSheetBehavior.setPeekHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics())
        );*/

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        bottomSheetDialog.show();
    }


}