package com.example.usama.adhaan;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    private static final String TAG= "Dashboard";

    private SectionsPageAdapter sectionsPageAdapter;

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Log.d(TAG, "onCreate: Starting.");
        sectionsPageAdapter= new SectionsPageAdapter(getSupportFragmentManager());

        viewPager= findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout=findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter= new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new prayerFragment(), "SALAH");
        adapter.addFragment(new duaFragment(), "DUA");
        viewPager.setAdapter(adapter);
    }
}
