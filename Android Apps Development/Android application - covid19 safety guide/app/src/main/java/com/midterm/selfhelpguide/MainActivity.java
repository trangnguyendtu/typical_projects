package com.midterm.selfhelpguide;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.selfhelpguide.Fragments.LiveNewsFragment;
import com.midterm.selfhelpguide.Fragments.SafetyTipsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDrawer();
        replaceFragment("safetytips");

    }

    private void replaceFragment(String code) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (code) {
            case "realtimemap":
                ft.replace(R.id.main_layout, new LiveMap());
                break;
            case "realtimestats":
                ft.replace(R.id.main_layout, new LiveNewsFragment("https://covid19.who.int/"));
                break;
            case "guardian":
                ft.replace(R.id.main_layout, new LiveNewsFragment("https://www.theguardian.com/world/series/coronavirus-live"));
                break;
            case "bbc":
                ft.replace(R.id.main_layout, new LiveNewsFragment("https://www.bbc.com/news/topics/cyz0z8w0ydwt/coronavirus-pandemic"));
                break;
            case "cnn":
                ft.replace(R.id.main_layout, new LiveNewsFragment("https://www.channelnewsasia.com/news/topics/coronavirus-covid-19"));
                break;

            default:
                ft.replace(R.id.main_layout, new SafetyTipsFragment());
                break;
        }


        ft.commit();
    }

    private void setupDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.home_drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();// creates call to onPrepareOptionsMenu()

            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.nv);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.safety_tips:
                        replaceFragment("safetytips");
                        getSupportActionBar().setTitle("Coronavirus Safety Guide");
                        break;
                    case R.id.realtime_map:
                        replaceFragment("realtimemap");
                        getSupportActionBar().setTitle("Real Time Map");
                        break;
                    case R.id.realtime_stat:
                        replaceFragment("realtimestats");
                        getSupportActionBar().setTitle("Real Time Statistics");
                        break;
                    case R.id.guardian_live:
                        replaceFragment("guardian");
                        getSupportActionBar().setTitle("Guardian Live News");
                        break;
                    case R.id.bbc_live:
                        replaceFragment("bbc");
                        getSupportActionBar().setTitle("BBC Live News");
                        break;
                    case R.id.cna_live:
                        replaceFragment("cnn");
                        getSupportActionBar().setTitle("CNN Asia Live");
                        break;
                    default:
                        return true;
                }

                drawerLayout.closeDrawers();


                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
