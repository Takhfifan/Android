package com.example.takhfif01.fragment.moreInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takhfif01.R;



public class AboutShopFragment extends Fragment {



    public AboutShopFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AboutShopFragment newInstance() {
        AboutShopFragment fragment = new AboutShopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_about_shop, container, false);



        return view;
    }
}