package com.yarolegovich.slidingrootnav.sample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yarolegovich.slidingrootnav.sample.fragment.InProgressListings;
import com.yarolegovich.slidingrootnav.sample.fragment.MyListings;

public class TabAdapter extends FragmentPagerAdapter {
    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new MyListings();
        }


        else{
            return new InProgressListings();
        }

    }




    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "MyListings";
        }
        else{
            return "InProgressListings";
        }

    }
}
