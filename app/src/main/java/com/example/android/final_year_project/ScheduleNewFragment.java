package com.example.android.final_year_project;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import adapters.MeetingListAdapter;
import prefs.Meeting;
import prefs.MeetingInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleNewFragment extends Fragment {
    FragmentManager FM;
    FragmentTransaction FT;
    Context context;
    Meeting addNew;
    MeetingInfo savedExpenses;
    List<Meeting> expenses;
    MeetingListAdapter expenseListAdapter;
    Button addExpense,date;
    EditText dateview, from, to, objective;
    Calendar calendar;
    private int year, month, day;

    public ScheduleNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        return inflater.inflate(R.layout.fragment_add_meeting, container, false);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        getActivity().showDialog(999);
        Toast.makeText(context, "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 999:
                // set date picker as current date
                return new DatePickerDialog(context, myDateListener,year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
    };
    private void showDate(int year, int month, int day) {
        dateview.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    public void onViewCreated(View v, Bundle savedInstanceState){
        FM= ((AppCompatActivity) context).getSupportFragmentManager();
        dateview = (EditText) getActivity().findViewById(R.id.meeting_date);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        final Date date_t= new Date(year,month,day);
        showDate(year, month+1, day);;

        savedExpenses = new MeetingInfo(context);
        addExpense = (Button)getActivity().findViewById(R.id.btn_add_meeting);
        date = (Button)getActivity().findViewById(R.id.date_setter);
        from = (EditText)getActivity().findViewById(R.id.meeting_from);
        to = (EditText)getActivity().findViewById(R.id.meeting_to);
        objective = (EditText)getActivity().findViewById(R.id.meeting_objective);

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });

        addExpense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String from_t = from.getText().toString();
                String to_t = to.getText().toString();
                String obj = objective.getText().toString();
                Meeting expense = new Meeting(date_t, from_t, to_t, obj);
                savedExpenses.addMeeting(expense);
                expenses = savedExpenses.getMeeting();
                Log.d("meetings", expenses.toString());
                expenses.add(expense);
                Toast.makeText(context,context.getResources().getString(R.string.added_expense),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
