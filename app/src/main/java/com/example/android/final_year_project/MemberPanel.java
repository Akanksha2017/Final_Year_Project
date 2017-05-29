package com.example.android.final_year_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MemberPanel extends AppCompatActivity {
    public static final String CATEGORY = "finalproject.abp.ecadroid.CATEGORY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_panel);
    }

    public void councilLogin(View view){
        //intent, to bind the two activities, current and the one to be opened, on runtime, w.r.t context, i.e. the button
        Intent intent = new Intent(this, LoginActivity.class); //Intent takes two parameters (context, Class)
        String category = "council";
        intent.putExtra(CATEGORY, category);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void presidentLogin(View view){
        Intent intent = new Intent(this, societyHomescreen.class); //Intent takes two parameters (context, Class)
        String category = "president";
        intent.putExtra(CATEGORY, category);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //finish();
    }

    public void memberLogin(View view){
        Intent intent = new Intent(this, memberHomescreen.class); //Intent takes two parameters (context, Class)
        String category = "member";
        intent.putExtra(CATEGORY, category);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //finish();
    }

    public void noLogin(View view){

    }
}
