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
import com.example.takhfif01.model.profileEditListItem;
import com.example.takhfif01.model.profileListItem;

import java.util.ArrayList;

public class profileEditMenuAdapter extends ArrayAdapter {

    public int resourceId;
    public Activity context;
    public ArrayList<profileEditListItem> object;


    public profileEditMenuAdapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);

        this.resourceId=resource;
        this.context=context;
        this.object=objects;


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
                    Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
