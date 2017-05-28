package com.example.android.final_year_project;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.List;

import adapters.ExpenseListAdapter;
import prefs.BudgetInfo;
import prefs.Expense;

import static java.lang.Float.parseFloat;


public class BudgetFragment extends Fragment {
    FragmentManager FM;
    FragmentTransaction FT;
    private BudgetInfo budgetInfo;
    private List<Expense> savedExpenses;
    ExpenseListAdapter expenseListAdapter;
    Context context;
    ListView expenseListView;

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
        context = getActivity();
        budgetInfo = new BudgetInfo(context);
        View view = inflater.inflate(R.layout.fragment_budget, container,
                false);
        findViewsById(view);

        // Inflate the layout for this fragment
        return view;

    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){

        ((AppCompatActivity) context).getSupportActionBar().setTitle(R.string.budget);
        MaterialBetterSpinner spinner = (MaterialBetterSpinner) getActivity().findViewById(R.id.android_material_design_spinner);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        FM= ((AppCompatActivity) context).getSupportFragmentManager();

        final TextView totalAmount = (TextView) getView().findViewById(R.id.budget_value);
        String amount = getBudget();
        totalAmount.setText(amount);
        budgetInfo.setTotal(parseFloat(amount + "f"));
        final TextView leftAmount = (TextView) getView().findViewById(R.id.budget_remaining_value);
        leftAmount.setText(budgetInfo.getKeyLeft().toString());

        if(savedExpenses!=null){
            expenseListAdapter = new ExpenseListAdapter(context, savedExpenses);
            expenseListView.setAdapter(expenseListAdapter);
        }

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

    private String getBudget(){
        return "50000";
    }
    private void findViewsById(View view) {
        expenseListView = (ListView) view.findViewById(R.id.list_expense);
    }
}
