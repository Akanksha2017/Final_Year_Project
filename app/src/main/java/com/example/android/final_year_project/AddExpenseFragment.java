package com.example.android.final_year_project;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Float.floatToIntBits;
import static prefs.BudgetInfo.COUNT;
import static prefs.BudgetInfo.LEFT;
import adapters.ExpenseListAdapter;
import prefs.Expense;
import prefs.BudgetInfo;

import static java.lang.Float.parseFloat;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddExpenseFragment extends Fragment {
    FragmentManager FM;
    FragmentTransaction FT;
    Context context;
    Expense addNew;
    BudgetInfo savedExpenses;
    List<Expense> expenses;
    ExpenseListAdapter expenseListAdapter;
    Button addExpense;
    EditText amountInput;
    EditText detailInput;

    ListView expenseListView;

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
        View v = inflater.inflate(R.layout.fragment_add_expense, container, false);
        context = getActivity();
        //expenses = new ArrayList<Expense>();
        //expenseListAdapter = new ExpenseListAdapter(context, expenses);
        //addExpense = (Button) getView().findViewById(R.id.btn_add_expense);
        //amountInput = (EditText) getView().findViewById(R.id.expense_amount);
        //detailInput = (EditText) getView().findViewById(R.id.expense_details);
        //expenseListView.setAdapter(expenseListAdapter);

        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        FM= ((AppCompatActivity) context).getSupportFragmentManager();


        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        TextView dateValue = (TextView)getActivity().findViewById(R.id.expense_date);
        dateValue.setText(date);
        savedExpenses = new BudgetInfo(context);
        addExpense = (Button)getActivity().findViewById(R.id.btn_add_expense);
        amountInput = (EditText)getActivity().findViewById(R.id.expense_amount);
        detailInput = (EditText) getActivity().findViewById(R.id.expense_details);
        addExpense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("addExpense", "I was called");
                FT = FM.beginTransaction();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                Float amount = parseFloat(amountInput.getText().toString()+"f");
                String details = detailInput.getText().toString();
                int count = COUNT;
                int id = count+1;
                COUNT = id;
                LEFT = LEFT-amount;
                Expense expense = new Expense(id,date,details,amount);
                savedExpenses.addExpense(expense);
                expenses = savedExpenses.getExpense();
                Log.d("expenses",expenses.toString());
                expenses.add(expense);

                Toast.makeText(context,
                        context.getResources().getString(R.string.added_expense),
                        Toast.LENGTH_SHORT).show();
                /*Bundle arg = new Bundle();
                arg.putFloat("amount", amount);
                arg.putString("details", details);
                arg.putInt("id", id);
                arg.putString("date", date);
                Intent intent = new Intent();
                intent.putExtras(arg);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);*/
                FM.popBackStack();
            }
        });
    }




}
