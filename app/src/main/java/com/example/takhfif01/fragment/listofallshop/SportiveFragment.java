package com.example.takhfif01.fragment.listofallshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.takhfif01.R;

public class SportiveFragment extends Fragment {


    public SportiveFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SportiveFragment newInstance() {
        SportiveFragment fragment = new SportiveFragment();
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
        return inflater.inflate(R.layout.fragment_sportive, container, false);
    }
}