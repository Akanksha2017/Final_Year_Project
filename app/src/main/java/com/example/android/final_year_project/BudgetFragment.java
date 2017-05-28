package com.example.android.final_year_project;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import static prefs.BudgetInfo.LEFT;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import adapters.ExpenseListAdapter;
import prefs.BudgetInfo;
import prefs.Expense;

import static java.lang.Float.parseFloat;


public class BudgetFragment extends Fragment {

    static int FRAGMENT_CODE=2;
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

        View view = inflater.inflate(R.layout.fragment_budget, container,
                false);
        findViewsById(view);

        // Inflate the layout for this fragment
        return view;

    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){

        generateView();
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddExpenseFragment();
                fragment.setTargetFragment(fragment, FRAGMENT_CODE);
                int title = R.string.label_add_expense;
                if (fragment != null) {
                    FT= FM.beginTransaction();
                    FT.replace(R.id.containerView, fragment);
                    FT.addToBackStack(null);
                    FT.commit();
                }
            }
        });
        String amount = getBudget();
        budgetInfo.setTotal(parseFloat(amount + "f"));

    }

    private String getBudget(){
        return "50000";
    }
    private void findViewsById(View view) {
        expenseListView = (ListView) view.findViewById(R.id.list_expense);
    }

    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Log.d("budget", "I am back");
        if (requestCode == FRAGMENT_CODE && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            Log.d("bundle", bundle.toString());
            if (bundle != null) {
                String date = bundle.getString("date");
                String details = bundle.getString("details");
                int id = bundle.getInt("id");
                Float amount = bundle.getFloat("amount");
                Expense expense = new Expense(id,date,details,amount);
                savedExpenses = new ArrayList<Expense>();
                Log.d("Expenses",expense.toString());
                savedExpenses.add(expense);
                Log.d("expenses",savedExpenses.toString());
                budgetInfo.addExpense(expense);
                Toast.makeText(context,
                        context.getResources().getString(R.string.added_expense),
                        Toast.LENGTH_SHORT).show();
            }
        }
        generateView();
    }*/

    public void generateView(){
        budgetInfo = new BudgetInfo(context);
        ((AppCompatActivity) context).getSupportActionBar().setTitle(R.string.budget);
        MaterialBetterSpinner spinner = (MaterialBetterSpinner) getActivity().findViewById(R.id.android_material_design_spinner);


        FM= ((AppCompatActivity) context).getSupportFragmentManager();

        final TextView totalAmount = (TextView) getView().findViewById(R.id.budget_value);
        String amount = getBudget();
        totalAmount.setText(amount);
        final TextView leftAmount = (TextView) getView().findViewById(R.id.budget_remaining_value);
        leftAmount.setText(LEFT.toString());

        List<Expense> allExpenses= budgetInfo.getExpense();
        expenseListView = (ListView) getActivity().findViewById(R.id.list_expense);
        if (allExpenses != null) {
            expenseListAdapter = new ExpenseListAdapter(context, allExpenses);
            expenseListView.setAdapter(expenseListAdapter);
        }

    }

}
