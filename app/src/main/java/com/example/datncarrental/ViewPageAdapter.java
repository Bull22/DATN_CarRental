package com.example.datncarrental;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> arrFragment = new ArrayList<>();
    ArrayList<String> arrTitle = new ArrayList<>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrTitle.size();
    }

    //method getPageTitle
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrTitle.get(position);
    }

    //method add fragment va tittle
    public  void addFragment(Fragment fragment, String title){
        arrFragment.add(fragment);
        arrTitle.add(title);
    }
}
