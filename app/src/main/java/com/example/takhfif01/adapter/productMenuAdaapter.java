package com.example.takhfif01.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.takhfif01.R;
import com.example.takhfif01.model.productMenuListItem;

import java.util.ArrayList;

public class productMenuAdaapter extends ArrayAdapter {

    public int resourceId;
    public Activity context;
    public ArrayList<productMenuListItem> object;


    public productMenuAdaapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);

        this.resourceId=resource;
        this.context=context;
        this.object=objects;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = this.context.getLayoutInflater().inflate(this.resourceId, null);

        TextView txtTitle = view.findViewById(R.id.productTitleItem);

        productMenuListItem productMenuListItem = object.get(position);
        txtTitle.setText(productMenuListItem.title);
        return view;
    }
}
