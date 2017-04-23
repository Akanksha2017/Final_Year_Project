package com.example.android.final_year_project;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingsFragment extends Fragment {

    public  static TabLayout tabLayout;
    public  static ViewPager viewPager;
    public  static int int_items= 3;



    public MeetingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_meetings,null);
        tabLayout=(TabLayout)v.findViewById(R.id.tabs_meetings);
        viewPager=(ViewPager)v.findViewById(R.id.viewpager_meetings);
        //set an adpater

        viewPager.setAdapter(new MyAdapterMeetings( getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return v;
    }

}