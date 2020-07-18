package com.example.takhfif01.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.takhfif01.R;
import com.example.takhfif01.model.settingMenuListItem;

import java.util.ArrayList;

public class settingMenuAdapter extends ArrayAdapter {

    public int resourceId;
    public Activity context;
    public ArrayList<settingMenuListItem> object;


    public settingMenuAdapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);

        this.resourceId=resource;
        this.context=context;
        this.object=objects;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = this.context.getLayoutInflater().inflate(this.resourceId, null);

        TextView txtTitle = view.findViewById(R.id.settingTitleItem);

        settingMenuListItem settingMenuListItem = object.get(position);
        txtTitle.setText(settingMenuListItem.title);
        return view;
    }
}
