package com.example.android.final_year_project;

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
import android.widget.Toast;

public class memberHomescreen extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager FM;
    FragmentTransaction FT;
    boolean flag = false;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView= (NavigationView) findViewById(R.id.shitstuff);
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title_activity_home_screen);

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
                    fragment = new SocietyTaskPoolFragment();
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
                    FT.addToBackStack(null);
                    FT.commit();
                }
                return false;
            }
        });

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    @Override
    public void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(R.string.title_activity_home_screen);
    }
    @Override
    public void onRestart() {
        super.onRestart();
        getSupportActionBar().setTitle(R.string.title_activity_home_screen);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {
            getSupportFragmentManager().popBackStack();
            if (getSupportFragmentManager().getBackStackEntryCount() == 1)
                getSupportActionBar().setTitle(R.string.title_activity_home_screen);
        }
        else{
            //super.onBackPressed();
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again to exit the application", Toast.LENGTH_SHORT).show();
        }
    }
}