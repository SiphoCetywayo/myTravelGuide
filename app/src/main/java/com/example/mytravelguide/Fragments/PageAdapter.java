package com.example.mytravelguide.Fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/*Note: Android FragmentPagerAdapter is used when you have a limited number of tabs that you want to swipe through.
If you have a dynamic tabs then you need to use Android FragmentStatePagerAdapter.*/

public class PageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;
    public PageAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HotelsFragment();
            case 1:
                return new AttractionsFragment();
            case 2:
                return new RestaurantsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return numOfTabs;
    }
}
