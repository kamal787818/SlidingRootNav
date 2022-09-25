package com.yarolegovich.slidingrootnav.sample;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.sample.fragment.Listings;
import com.yarolegovich.slidingrootnav.sample.fragment.Send_Feedback;
import com.yarolegovich.slidingrootnav.sample.menu.DrawerAdapter;
import com.yarolegovich.slidingrootnav.sample.menu.DrawerItem;
import com.yarolegovich.slidingrootnav.sample.menu.SimpleItem;
import com.yarolegovich.slidingrootnav.sample.menu.SpaceItem;
import com.yarolegovich.slidingrootnav.sample.fragment.CenteredTextFragment;

import java.util.Arrays;



public class SampleActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_CART = 3;
    private static final int POS_PAYMENT = 4;
    private static final int POS_GRowth = 5;
    private static final int POS_PRICE_RECOOMENDATION = 6;
    private static final int POS_Seller_Prestige = 7;
    private static final int POS_Performance = 8;
    private static final int POS_Buyer_Question = 9;
    private static final int POS_My_Tickets = 10;
    private static final int POS_SEND_FEEDBACK= 11;



    private static final int POS_LOGOUT = 13;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_CART),
                createItemFor(POS_PAYMENT),
                createItemFor(POS_GRowth),
                createItemFor(POS_PRICE_RECOOMENDATION),
                createItemFor(POS_Seller_Prestige),
                createItemFor(POS_Performance),
                createItemFor(POS_Buyer_Question),
                createItemFor(POS_My_Tickets),
                createItemFor(POS_SEND_FEEDBACK),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
                adapter.setListener(this);




                RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setListener(new DrawerAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                switch (position){
                    case 1:
                        Toast.makeText(SampleActivity.this, "Ho  Raha Hai", Toast.LENGTH_SHORT).show();
                      getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,new Listings()).commit();

                        slidingRootNav.closeMenu();
                        Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
                        showFragment(selectedScreen);
                      break;
                    case 11:
                        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,new Send_Feedback()).commit();

                        slidingRootNav.closeMenu();
                        Fragment selectedScreen1 = CenteredTextFragment.createFor(screenTitles[position]);
                        showFragment(selectedScreen1);

                }
            }
        });

        adapter.setSelected(POS_DASHBOARD);
    }

    @Override
    public void onItemSelected(int position) {
        if (position == POS_LOGOUT) {
            finish();
        }
        slidingRootNav.closeMenu();
     Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        showFragment(selectedScreen);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
}
