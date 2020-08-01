package com.example.takhfif01.fragment.moreInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takhfif01.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressShopFragment extends Fragment {

    public AddressShopFragment() {
        // Required empty public constructor
    }


    public static AddressShopFragment newInstance() {
        AddressShopFragment fragment = new AddressShopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_address_shop, container, false);


        return view;
    }
}