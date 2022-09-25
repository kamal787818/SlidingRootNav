package com.yarolegovich.slidingrootnav.sample.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yarolegovich.slidingrootnav.sample.R;


public class MyListings extends Fragment {
    Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_my_listings, container, false);
btn=view.findViewById(R.id.newlistings);
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
         getFragmentManager().beginTransaction().replace(R.id.activity_main,new ListingSearch()).commit();
    }
});
       return view;
    }
}