package com.example.mytravelguide;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tabHotels;
    TabItem tabAttractions;
    TabItem tabRestaurants;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));

        tabLayout = findViewById(R.id.tabLayout);
        tabHotels = findViewById(R.id.tabAccommodations);
        tabAttractions = findViewById(R.id.tabAttractions);
        tabRestaurants = findViewById(R.id.tabRestaurants);
        viewPager = findViewById(R.id.viewPager);

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    private void addTabTexts() {

    }
}  

