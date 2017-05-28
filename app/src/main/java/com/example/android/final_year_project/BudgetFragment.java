package com.example.android.final_year_project;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class BudgetFragment extends Fragment {
    FragmentManager FM;
    FragmentTransaction FT;


    public BudgetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.budget);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_budget, container, false);

    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        Context context = getActivity();
        ((AppCompatActivity) context).getSupportActionBar().setTitle(R.string.budget);
        MaterialBetterSpinner spinner = (MaterialBetterSpinner) getActivity().findViewById(R.id.android_material_design_spinner);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        FM= ((AppCompatActivity) context).getSupportFragmentManager();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddExpenseFragment();
                int title = R.string.label_add_expense;
                if (fragment != null) {
                    FT= FM.beginTransaction();
                    FT.replace(R.id.containerView, fragment);
                    FT.addToBackStack(null);
                    FT.commit();
                }
            }
        });
    }
}
