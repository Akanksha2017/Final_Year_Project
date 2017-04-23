package com.example.android.final_year_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import static com.example.android.final_year_project.R.id.toolbar;

public class homescreen extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager FM;
    FragmentTransaction FT;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView= (NavigationView) findViewById(R.id.shitstuff);
        final android.support.v7.widget.Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FM= getSupportFragmentManager();
//        FT= FM.beginTransaction();
//        FT.replace(R.id.containerView, new ProfileFragment()).commit();
//        toolbar.setTitle(R.string.profile);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                Fragment fragment = null;
                int title = 0;
                if (item.getItemId()== R.id.nav_budget) {
                    fragment = new BudgetFragment();
                    title = R.string.budget;
                }

                if (item.getItemId()==R.id.nav_profile)
                {
                    fragment = new ProfileFragment();
                    title = R.string.profile;

                }

                if (item.getItemId()==R.id.nav_task)
                {
                    fragment = new TaskPoolFragment();
                    title = R.string.task;

                }
                if (item.getItemId()==R.id.nav_meeting)
                {
                    fragment = new MeetingsFragment();
                    title = R.string.Meet;

                }
                if (item.getItemId()==R.id.nav_calender)
                {
                    fragment = new CalenderFragment();
                    title = R.string.Calender;

                }
                if (item.getItemId()==R.id.nav_log)
                {
                    fragment = new AttendanceFragment();
                    title = R.string.log;

                }
                if (item.getItemId()==R.id.nav_society)
                {
                    fragment = new CreateSocietyFragment();
                    title = R.string.CreateSoc;

                }
                if (fragment != null) {
                    flag = true;
                    FT= FM.beginTransaction();
                    FT.replace(R.id.containerView, fragment);
                    toolbar.setTitle(title);
                    //FT.addToBackStack(null);
                    FT.commit();
                }
                return false;
            }
        });



        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setTitle(R.string.app_name);


    }
    @Override
    public void onBackPressed() {
        if (flag) {
            while (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();}
            Intent home=new Intent(this,homescreen.class);
            startActivity(home);
            flag = false;

        }
    }
}