package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.timekeepingmanagement.R;
import com.example.timekeepingmanagement.adapter.EmployeeAdapter;
import com.example.timekeepingmanagement.adapter.ProductAdapter;
import com.example.timekeepingmanagement.entity.Employee;
import com.example.timekeepingmanagement.entity.Product;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {
    ListView lvListEmployee;
    DataBase db;
    ArrayList<Employee> data = new ArrayList<>();
    EmployeeAdapter employeeAdapter;
    ImageView ivAddEmployee;


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
        ivAddEmployee = findViewById(R.id.ivAddEmployee);
    }

    void setEvent(){
        init();
        employeeAdapter = new EmployeeAdapter(this,R.layout.raw_product,data);
        lvListEmployee.setAdapter(employeeAdapter);
        ivAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeActivity.this, AddEmployeeActivity.class);
                intent.putExtra("isAdd",true);
                startActivity(intent);
            }
        });
    }

    void init(){
        data.addAll(db.readEmployees());
    }
}