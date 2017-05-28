package com.example.android.final_year_project;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class PresidentTaskPoolFragment extends Fragment {
    //final android.support.v7.widget.Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

    FragmentManager FM;
    FragmentTransaction FT;

    public PresidentTaskPoolFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.task);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.task);
        return inflater.inflate(R.layout.fragment_president_task_pool, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        Context context = getActivity();
        ((AppCompatActivity) context).getSupportActionBar().setTitle(R.string.task);
        MaterialBetterSpinner spinner = (MaterialBetterSpinner) getActivity().findViewById(R.id.android_material_design_spinner);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab1);
        FM = ((AppCompatActivity) context).getSupportFragmentManager();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new NewTaskFragment();
                int title = R.string.label_add_task;
                if (fragment != null) {
                    FT = FM.beginTransaction();
                    FT.replace(R.id.containerView, fragment);
                    FT.addToBackStack(null);
                    FT.commit();
                }
            }
        });
    }
}
