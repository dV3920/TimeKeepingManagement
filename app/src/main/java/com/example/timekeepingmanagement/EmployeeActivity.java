package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ListView;

import com.example.timekeepingmanagement.adapter.EmployeeAdapter;
import com.example.timekeepingmanagement.entity.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {
    ListView lvListEmployee;
    DataBase db;
    ArrayList<Employee> data = new ArrayList<>();
    EmployeeAdapter employeeAdapter;
    FloatingActionButton btnAdd;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        setControl();
        setEvent();
    }

    void  setControl(){
        db = new DataBase(getApplicationContext());
        lvListEmployee = findViewById(R.id.lvListEmployee);
        btnAdd = findViewById(R.id.floatingActionButton2);
        searchView = findViewById(R.id.search);
    }

    void setEvent(){
        init();
        employeeAdapter = new EmployeeAdapter(this,R.layout.raw_employee,data);
        lvListEmployee.setAdapter(employeeAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeActivity.this, AddEmployeeActivity.class);
                intent.putExtra("isAdd",true);
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                employeeAdapter.search(query);
                return false;
            }
        });
    }

    void init(){
        data.addAll(db.readEmployees());
    }
}