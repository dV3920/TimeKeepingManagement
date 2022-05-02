package com.example.timekeepingmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.timekeepingmanagement.fragment.EmployeePlusFragment;
import com.example.timekeepingmanagement.fragment.ListEmployeeFragment;
import com.example.timekeepingmanagement.fragment.UpdateEmployeeFragment;
import com.google.android.material.navigation.NavigationView;

public class EmployeeActivity extends AppCompatActivity {
   // SearchView searchView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navView;
  //  Menu myMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        replaceFragment(new ListEmployeeFragment());
        setControl();
        setEvent();

    }

    void  setControl(){
     //   searchView = findViewById(R.id.searchView);
        drawerLayout = findViewById(R.id.employee_draw);
        navView = findViewById(R.id.employee_nav_menu);

    }


    void setEvent(){
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case  R.id.list_employee_menu:
                        replaceFragment(new ListEmployeeFragment());
                        break;
                    case R.id.add_employee_menu:
                        replaceFragment(new EmployeePlusFragment());
                        break;
                    case R.id.update_employee_menu:
                        replaceFragment(new UpdateEmployeeFragment());
                        break;
                    case R.id.back_home_menu:
                        finish();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_menu, menu);
//        myMenu = menu;
//        return super.onCreateOptionsMenu(menu);
//    }

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

    protected void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_section, fragment);
        fragmentTransaction.commit();

    }

}