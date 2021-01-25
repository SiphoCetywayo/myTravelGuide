package com.example.mytravelguide.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mytravelguide.Fragments.AttractionsFragment;
import com.example.mytravelguide.Fragments.HotelsFragment;
import com.example.mytravelguide.Fragments.Amusement_parkFragment;
import com.example.mytravelguide.Fragments.RestaurantsFragment;

/*Note: Android FragmentPagerAdapter is used when you have a limited number of tabs that you want to swipe through.
If you have a dynamic tabs then you need to use Android FragmentStatePagerAdapter.*/

public class PageAdapter extends FragmentStatePagerAdapter {
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
                HotelsFragment tab1 = new HotelsFragment();
                return  tab1;
            case 1:
                AttractionsFragment tab2 = new AttractionsFragment();
                return tab2;
            case 2:
               RestaurantsFragment tab3 = new RestaurantsFragment();
                return tab3;

            case 3:
                Amusement_parkFragment tab4 = new Amusement_parkFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return numOfTabs;
    }
}
