package com.mg.hotelmanagementsystem.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mg.hotelmanagementsystem.fragments.MealsFragment;
import com.mg.hotelmanagementsystem.fragments.OrdersFragment;
import com.mg.hotelmanagementsystem.fragments.TablesFragment;

/**
 * Created by moses on 7/29/18.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles;

    public MainViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OrdersFragment();
            case 1:
                return new MealsFragment();
            case 2:
                return new TablesFragment();
        }
        return new Fragment();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
