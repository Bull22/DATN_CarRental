package com.example.datncarrental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class CarRentalActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListCarAdapter listCarAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    ViewPageAdapter viewPagerAdapter;
    String[] itemCars = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        itemCars = getResources().getStringArray(R.array.carlist);
        initViewPager();
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout1);
        viewPagerAdapter = new ViewPageAdapter(this.getSupportFragmentManager());
        for (int i = 0; i< itemCars.length;i++){
            viewPagerAdapter.addFragment(new ClassFragment(),itemCars[i].toString());
        }

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}