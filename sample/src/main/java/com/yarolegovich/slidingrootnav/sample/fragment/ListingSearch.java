package com.yarolegovich.slidingrootnav.sample.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yarolegovich.slidingrootnav.sample.R;


public class ListingSearch extends Fragment {
Button btn;
EditText ed1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_listing_search, container, false);
        btn=view.findViewById(R.id.Btn);
        ed1=view.findViewById(R.id.Search);
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "Ho Gaya na", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.activity_main,new ProductList()).commit();

            }
        });
        return view;
    }
}