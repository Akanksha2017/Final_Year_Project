package com.example.android.final_year_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static com.example.android.final_year_project.CreateSocietyFragment.int_items;

public class MyAdapterCreateSoc extends FragmentPagerAdapter {


    public MyAdapterCreateSoc(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CreateSocietySubFragment();
            case 1:
                return new ViewSocietyFragment();
        }
        return null;
    }

    @Override
    public int getCount() {


        return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Create New Society";
            case 1:
                return "View Societies";


        }

        return null;
    }
}