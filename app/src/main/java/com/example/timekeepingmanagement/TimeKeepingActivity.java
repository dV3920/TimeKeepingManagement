package com.example.timekeepingmanagement;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.timekeepingmanagement.fragment.EmployeePlusFragment;
import com.example.timekeepingmanagement.fragment.ListEmployeeFragment;
import com.example.timekeepingmanagement.fragment.ListTimeKeepingFragment;
import com.example.timekeepingmanagement.fragment.TimeKeepingPlusFragment;
import com.example.timekeepingmanagement.fragment.UpdateEmployeeFragment;
import com.google.android.material.navigation.NavigationView;

public class TimeKeepingActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timekeeping);
        replaceFragment(new ListTimeKeepingFragment());
        setControl();
        setEvent();

    }

    private void setControl() {
        drawerLayout = findViewById(R.id.timekeeping_draw);
        navView = findViewById(R.id.timekeeping_nav_menu);
    }

    private void setEvent() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case  R.id.list_timekeeping_menu:
                        replaceFragment(new ListTimeKeepingFragment());
                        break;
                    case R.id.add_timekeeping_menu:
                        replaceFragment(new TimeKeepingPlusFragment());
                        break;
//                    case R.id.update_timekeeping_menu:
//                        replaceFragment(new UpdateEmployeeFragment());
//                        break;
                    case R.id.back_home_menu:
                        finish();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() == R.id.menu_action_search){
//            Toast.makeText(this,"Hello search", Toast.LENGTH_SHORT).show();
//        }

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_section, fragment);
        fragmentTransaction.commit();
    }
}
