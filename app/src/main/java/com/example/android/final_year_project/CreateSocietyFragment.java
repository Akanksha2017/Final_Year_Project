package com.example.android.final_year_project;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateSocietyFragment extends Fragment {
    public  static TabLayout tabLayout;
    public  static ViewPager viewPager;
    public  static int int_items= 2;
    //final android.support.v7.widget.Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

    public CreateSocietyFragment() {
        // Required empty public constructor
    }

    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.CreateSoc);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_society,null);
        tabLayout=(TabLayout)v.findViewById(R.id.tabs_create_society);
        viewPager=(ViewPager)v.findViewById(R.id.viewpager_create_society);
        //set an adpater

        viewPager.setAdapter(new MyAdapterCreateSoc( getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.CreateSoc);
        return v;
    }

}
