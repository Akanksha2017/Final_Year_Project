package com.example.android.final_year_project;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import adapters.MeetingListAdapter;
import prefs.Meeting;
import prefs.MeetingInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingLogFragment extends Fragment {
    private MeetingInfo budgetInfo;
    private List<Meeting> savedExpenses;
    MeetingListAdapter expenseListAdapter;
    Context context;
    ListView expenseListView;

    public MeetingLogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();

        View view = inflater.inflate(R.layout.fragment_meeting_log, container,
                false);
        findViewsById(view);

        // Inflate the layout for this fragment
        return view;
    }

    public void onViewCreated(View v, Bundle savedInstanceState){
        context = getActivity();
        budgetInfo = new MeetingInfo(context);


        List<Meeting> allExpenses= budgetInfo.getMeeting();
        expenseListView = (ListView) getView().findViewById(R.id.list_meeting);
        if (allExpenses != null) {
            expenseListAdapter = new MeetingListAdapter(context, allExpenses);
            expenseListView.setAdapter(expenseListAdapter);
        }
    }

    private void findViewsById(View view) {
        expenseListView = (ListView) view.findViewById(R.id.list_meeting);
    }

}
