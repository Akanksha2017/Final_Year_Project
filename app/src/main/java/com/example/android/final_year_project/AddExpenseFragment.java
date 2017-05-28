package com.example.android.final_year_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddExpenseFragment extends Fragment {
    //final android.support.v7.widget.Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

    public AddExpenseFragment() {
        // Required empty public constructor
    }

    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.label_add_expense);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.label_add_expense);

        return inflater.inflate(R.layout.fragment_add_expense, container, false);
    }

}
