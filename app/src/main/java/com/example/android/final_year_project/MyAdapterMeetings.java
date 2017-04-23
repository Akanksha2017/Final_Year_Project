package com.example.android.final_year_project;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import static com.example.android.final_year_project.MeetingsFragment.int_items;
/**
 * Created by Admin on 3/1/2017.
 */

public class MyAdapterMeetings  extends FragmentPagerAdapter {


    public MyAdapterMeetings(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MeetingLogFragment();
            case 1:
                return new ScheduleNewFragment();
            case 2:
                return new MyLogFragment();
        }
        return null;
    }

    @Override
    public int getCount() {


        return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position) {
            case 0:
                return "Meeting Log";
            case 1:
                return "Schedule New";
            case 2:
                return "My Log";
        }

        return null;
    }
}