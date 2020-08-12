package com.example.takhfif01.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takhfif01.R;
import com.example.takhfif01.model.profileEditListItem;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class profileEditMenuAdapter extends ArrayAdapter {

    public int resourceId;
    public Activity context;
    public ArrayList<profileEditListItem> object;


    public profileEditMenuAdapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);

        this.resourceId = resource;
        this.context = context;
        this.object = objects;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = this.context.getLayoutInflater().inflate(this.resourceId, null);

        ImageView img = view.findViewById(R.id.imgProfileEditMenuList);
        TextView txtTitle = view.findViewById(R.id.txtTitleProfileEditMenuList);
        TextView txtDesc = view.findViewById(R.id.txtDescProfileEditMenuList);
        LinearLayout lnr = view.findViewById(R.id.linearProfileEditMenuList);

        profileEditListItem profileEditListItem = object.get(position);
        txtTitle.setText(profileEditListItem.title);
        txtDesc.setText(profileEditListItem.desc);
        img.setImageResource(profileEditListItem.img);


        lnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();

                if (position == 0) {//نام و نام خانوادگی
                    showBottomSheetDialogChaneUserName();

                }if (position==1){//شماره تلفن همراه
                    showBottomSheetDialogChanePhoneNumber();

                } if (position==2){//پست الکترونیک
                    showBottomSheetDialogAddEmail();

                }if (position==3){//شماره کارت
                    showBottomSheetDialogAddAccCard();
                }


            }
        });

        return view;
    }

    private void showBottomSheetDialogAddAccCard() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.context);
        View parent = this.context.getLayoutInflater().inflate(R.layout.bottm_sheet_add_acc_number_profile, null);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        String accNumber = preferences.getString("accNumber", "");
        Log.i("TAG", "showBottomSheetDialogAddAccCard: accNumber="+accNumber);

        final EditText acc_card_add_profile = parent.findViewById(R.id.acc_card_add_profile);
        //final TextInputLayout acc_card_layout_add_profile = parent.findViewById(R.id.acc_card_layout_add_profile);

        acc_card_add_profile.setText(accNumber);

        final Button btn_add_acc_card_profile = parent.findViewById(R.id.btn_add_acc_card_profile);
        btn_add_acc_card_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String txt_acc_card_add_profile = acc_card_add_profile.getText().toString().trim();
                Log.i("TAG", "onClick:acc_card_add_profile "+txt_acc_card_add_profile);



                if (!(acc_card_add_profile.getText().toString().trim().length() >= 16)) {
                    //acc_card_layout_add_profile.setErrorEnabled(true);
                    //acc_card_layout_add_profile.setError("نام باید 16 رقم باشد");


                }else {
                    SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences2.edit();
                    editor.putString("accNumber", txt_acc_card_add_profile);
                    editor.commit();


                    Toast.makeText(context, "باموفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();

                }



            }
        });




        bottomSheetDialog.setContentView(parent);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());
        bottomSheetBehavior.setPeekHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, this.context.getResources().getDisplayMetrics())
        );

        bottomSheetDialog.show();
    }

    private void showBottomSheetDialogChanePhoneNumber() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.context);
        View parent = this.context.getLayoutInflater().inflate(R.layout.bottm_sheet_change_phone_nmber_profile, null);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        String number = preferences.getString("phoneNumber", "");
        Log.i("TAG", "showBottomSheetDialogChanePhoneNumber: number="+number);

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
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());
        bottomSheetBehavior.setPeekHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, this.context.getResources().getDisplayMetrics())
        );

        bottomSheetDialog.show();

    }

    private void showBottomSheetDialogAddEmail() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.context);
        View parent = this.context.getLayoutInflater().inflate(R.layout.bottm_sheet_add_email_profile, null);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        String email = preferences.getString("email", "");
        Log.i("TAG", "showBottomSheetDialogAddEmail: email="+email);

        final EditText email_add_profile = parent.findViewById(R.id.email_add_profile);
        //final TextInputLayout email_layout_add_profile = parent.findViewById(R.id.email_layout_add_profile);

        email_add_profile.setText(email);

        final Button btn_add_email = parent.findViewById(R.id.btn_add_email);
        btn_add_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String txt_email_add_profile = email_add_profile.getText().toString().trim();
                Log.i("TAG", "onClick:email_add_profile "+txt_email_add_profile);




                if (txt_email_add_profile.lastIndexOf("@") <= 0
                        || txt_email_add_profile.lastIndexOf(".") < txt_email_add_profile.lastIndexOf("@")
                        || txt_email_add_profile.split("@").length > 2){


                    //email_layout_add_profile.setErrorEnabled(true);
                   // email_layout_add_profile.setError("پست الکترونیک خود را درست وارد کنید!\nمثلا:myEmail@Info.com");
                }else {
                    SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences2.edit();
                    editor.putString("email", txt_email_add_profile);
                    editor.commit();


                    Toast.makeText(context, "باموفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();

                }



            }
        });




        bottomSheetDialog.setContentView(parent);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());
        bottomSheetBehavior.setPeekHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, this.context.getResources().getDisplayMetrics())
        );

        bottomSheetDialog.show();

    }

    private void showBottomSheetDialogChaneUserName() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.context);
        View parent = this.context.getLayoutInflater().inflate(R.layout.bottm_sheet_change_username_profile, null);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        String name = preferences.getString("name", "");
        String lastName = preferences.getString("lastName", "");
        Log.i("TAG", "showBottomSheetDialogChaneUserName: name"+name+"\n"+"lastname"+lastName);


        final EditText name_change_profile = parent.findViewById(R.id.name_change_profile);
        final EditText lastname_change_profile = parent.findViewById(R.id.lastname_change_profile);

        //final TextInputLayout name_layout_change_profile = parent.findViewById(R.id.name_layout_change_profile);
       // final TextInputLayout lastname_layout_change_profile = parent.findViewById(R.id.lastname_layout_change_profile);


        name_change_profile.setText(name);
        lastname_change_profile.setText(lastName);


        final Button btn_change_username = parent.findViewById(R.id.btn_change_username);
        btn_change_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt_name_change_profile = name_change_profile.getText().toString().trim();
                String txt_lastname_change_profile = lastname_change_profile.getText().toString().trim();
                String txt_username_change_profile = txt_name_change_profile + " " + txt_lastname_change_profile;
                Log.i("TAG", "onClick:txt_name_change_profile "+txt_name_change_profile+"\n"
                        +"txt_lastname_change_profile"+txt_lastname_change_profile+
                        "\n"+"txt_username_change_profile"+txt_username_change_profile);

                if (!(name_change_profile.getText().toString().trim().length() >= 3)) {
             //       name_layout_change_profile.setErrorEnabled(true);
             //       name_layout_change_profile.setError("نام باید حداقل 3 حرف باشد");
                } else {

                    if (!(lastname_change_profile.getText().toString().trim().length() >= 4)) {
                  //      lastname_layout_change_profile.setErrorEnabled(true);
                 //       lastname_layout_change_profile.setError("نام خانوادگی باید حداقل 4 حرف باشد");
                    } else {
                        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = preferences2.edit();
                        editor.putString("name", txt_name_change_profile);
                        editor.putString("lastName", txt_lastname_change_profile);
                        editor.putString("username", txt_username_change_profile);
                        editor.commit();

                        name_change_profile.setText(txt_name_change_profile);
                        lastname_change_profile.setText(txt_lastname_change_profile);

                        Toast.makeText(context, "باموفقیت انجام شد!", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();

                    }
                }


            }
        });


        bottomSheetDialog.setContentView(parent);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) parent.getParent());
        bottomSheetBehavior.setPeekHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, this.context.getResources().getDisplayMetrics())
        );

        bottomSheetDialog.show();

    }


}
