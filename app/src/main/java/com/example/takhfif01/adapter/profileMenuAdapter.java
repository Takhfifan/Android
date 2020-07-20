package com.example.takhfif01.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takhfif01.R;
import com.example.takhfif01.model.buyMenuListItem;
import com.example.takhfif01.model.profileListItem;

import java.util.ArrayList;

public class profileMenuAdapter extends ArrayAdapter {

    public int resourceId;
    public Activity context;
    public ArrayList<profileListItem> object;


    public profileMenuAdapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);

        this.resourceId=resource;
        this.context=context;
        this.object=objects;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = this.context.getLayoutInflater().inflate(this.resourceId, null);

        ImageView img = view.findViewById(R.id.imgProfileMenuList);
        TextView txtTitle = view.findViewById(R.id.txtTitleProfileMenuList);
        LinearLayout lnr = view.findViewById(R.id.linearProfileMenuList);

        profileListItem profileListItem = object.get(position);
        txtTitle.setText(profileListItem.title);
        img.setImageResource(profileListItem.img);


        lnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
