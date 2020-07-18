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

import java.util.ArrayList;

public class buyMenuAdapter extends ArrayAdapter {

    public int resourceId;
    public Activity context;
    public ArrayList<buyMenuListItem> object;


    public buyMenuAdapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);

        this.resourceId=resource;
        this.context=context;
        this.object=objects;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = this.context.getLayoutInflater().inflate(this.resourceId, null);

        ImageView img = view.findViewById(R.id.imgMenuList);
        TextView txtTitle = view.findViewById(R.id.txtTitleMenuList);
        LinearLayout lnr = view.findViewById(R.id.linearMenuList);

        buyMenuListItem buyMenuListItem = object.get(position);
        txtTitle.setText(buyMenuListItem.title);
        img.setImageResource(buyMenuListItem.img);


        lnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
