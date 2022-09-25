package com.yarolegovich.slidingrootnav.sample.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.yarolegovich.slidingrootnav.sample.R;
import com.yarolegovich.slidingrootnav.sample.TabAdapter;


public class Listings extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_listings, container, false);
        tabLayout =view. findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        TabAdapter adapter=new TabAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}