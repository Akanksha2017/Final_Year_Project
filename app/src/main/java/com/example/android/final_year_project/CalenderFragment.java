package com.example.android.final_year_project;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalenderFragment extends Fragment {
    //final android.support.v7.widget.Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

    public CalenderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.Calender);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calender, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.Calender);
        return rootView;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        Context context = getActivity();
        ((AppCompatActivity) context).getSupportActionBar().setTitle(R.string.Calender);
        MaterialBetterSpinner spinner = (MaterialBetterSpinner) getActivity().findViewById(R.id.android_material_design_spinner);
        CalendarView simpleCalendarView = (CalendarView) getActivity().findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate();

    }

}
