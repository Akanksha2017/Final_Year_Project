package com.example.android.final_year_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import prefs.UserSession;

public class MainActivity extends AppCompatActivity {

    private UserSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new UserSession(this);
        setContentView(R.layout.activity_main);
        if(session.isUserLoggedin()) {
            startActivity(new Intent(this, homescreen.class));
            finish();
        }

    }

    public void OpenMembersPanel(View view){
        Intent member = new Intent(this, MemberPanel.class);
        member.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(member);
        finish();
    }
}
