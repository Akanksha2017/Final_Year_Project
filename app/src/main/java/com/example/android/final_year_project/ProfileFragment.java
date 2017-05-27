package com.example.android.final_year_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import prefs.UserInfo;
import prefs.UserSession;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    //final android.support.v7.widget.Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    private UserSession session;
    private UserInfo userInfo;
    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.profile);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.profile);

        session         = new UserSession(getActivity());
        userInfo        = new UserInfo(getActivity());
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
