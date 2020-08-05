package com.example.takhfif01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.takhfif01.R;

public class SetCommentActivity extends AppCompatActivity {

    TextView setPointedt;
    LinearLayout linear_setPoint_set_comment;

    RadioButton noIdea_radiobtn_set_comment,good_radiobtn_set_comment,bad_radiobtn_set_comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_comment);
        init();
        setPoint();
        suggestHereRadioButton();
    }


    private void init() {
        setPointedt = findViewById(R.id.setPoinedt_set_comment);
        noIdea_radiobtn_set_comment = findViewById(R.id.noIdea_radiobtn_set_comment);
        good_radiobtn_set_comment = findViewById(R.id.good_radiobtn_set_comment);
        bad_radiobtn_set_comment = findViewById(R.id.bad_radiobtn_set_comment);
        linear_setPoint_set_comment = findViewById(R.id.linear_setPoint_set_comment);
    }
    private void suggestHereRadioButton() {
        noIdea_radiobtn_set_comment.setChecked(true);

        noIdea_radiobtn_set_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                good_radiobtn_set_comment.setChecked(false);
                bad_radiobtn_set_comment.setChecked(false);
            }
        });

        good_radiobtn_set_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noIdea_radiobtn_set_comment.setChecked(false);
                bad_radiobtn_set_comment.setChecked(false);

            }
        });

        bad_radiobtn_set_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noIdea_radiobtn_set_comment.setChecked(false);
                good_radiobtn_set_comment.setChecked(false);
            }
        });

    }

    private void setPoint() {
        linear_setPoint_set_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSetPointDialog();
            }
        });
    }

    private void showSetPointDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.alert_dialog_set_point);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final RadioButton veryBad_radio_button_alert_dialog = dialog.findViewById(R.id.veryBad_radio_button_alert_dialog);
        final RadioButton bad_radio_button_alert_dialog = dialog.findViewById(R.id.bad_radio_button_alert_dialog);
        final RadioButton med_radio_button_alert_dialog = dialog.findViewById(R.id.med_radio_button_alert_dialog);
        final RadioButton good_radio_button_alert_dialog = dialog.findViewById(R.id.good_radio_button_alert_dialog);
        final RadioButton veryGood_radio_button_alert_dialog = dialog.findViewById(R.id.veryGood_radio_button_alert_dialog);


        if (setPointedt.getText().equals("خیلی بد")){
            veryBad_radio_button_alert_dialog.setChecked(true);
        }else if (setPointedt.getText().equals("بد")){
            bad_radio_button_alert_dialog.setChecked(true);
        }else if (setPointedt.getText().equals("معمولی")){
            med_radio_button_alert_dialog.setChecked(true);
        }else if (setPointedt.getText().equals("خوب")){
            good_radio_button_alert_dialog.setChecked(true);
        }else if (setPointedt.getText().equals("عالی")){
            veryGood_radio_button_alert_dialog.setChecked(true);
        }


        veryBad_radio_button_alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veryBad_radio_button_alert_dialog.setChecked(true);

                bad_radio_button_alert_dialog.setChecked(false);
                med_radio_button_alert_dialog.setChecked(false);
                good_radio_button_alert_dialog.setChecked(false);
                veryGood_radio_button_alert_dialog.setChecked(false);

                setPointedt.setText("خیلی بد");

                dialog.dismiss();
            }
        });

        bad_radio_button_alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bad_radio_button_alert_dialog.setChecked(true);

                veryBad_radio_button_alert_dialog.setChecked(false);
                med_radio_button_alert_dialog.setChecked(false);
                good_radio_button_alert_dialog.setChecked(false);
                veryGood_radio_button_alert_dialog.setChecked(false);

                setPointedt.setText("بد");


                dialog.dismiss();
            }
        });

        med_radio_button_alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                med_radio_button_alert_dialog.setChecked(true);

                veryBad_radio_button_alert_dialog.setChecked(false);
                bad_radio_button_alert_dialog.setChecked(false);
                good_radio_button_alert_dialog.setChecked(false);
                veryGood_radio_button_alert_dialog.setChecked(false);

                setPointedt.setText("معمولی");

                dialog.dismiss();
            }
        });

        good_radio_button_alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                good_radio_button_alert_dialog.setChecked(true);

                veryBad_radio_button_alert_dialog.setChecked(false);
                bad_radio_button_alert_dialog.setChecked(false);
                med_radio_button_alert_dialog.setChecked(false);
                veryGood_radio_button_alert_dialog.setChecked(false);

                setPointedt.setText("خوب");

                dialog.dismiss();
            }
        });

        veryGood_radio_button_alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                veryGood_radio_button_alert_dialog.setChecked(true);

                veryBad_radio_button_alert_dialog.setChecked(false);
                bad_radio_button_alert_dialog.setChecked(false);
                med_radio_button_alert_dialog.setChecked(false);
                good_radio_button_alert_dialog.setChecked(false);

                setPointedt.setText("عالی");

                dialog.dismiss();
            }
        });


        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}