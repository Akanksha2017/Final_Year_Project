package com.example.android.final_year_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.android.final_year_project.R.id.toolbar;


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



}
