package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timekeepingmanagement.database.DataBase;
import com.example.timekeepingmanagement.entity.Employee;

public class ProfileActivity extends AppCompatActivity {
    EditText id, firstname, lastname, factory;
    Button btnSave;
    Employee employee;
    DataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
      //  Toast.makeText(this,getIntent().getStringExtra("username"),Toast.LENGTH_SHORT).show();
        setControl();
        setEvent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void setControl(){
        id = findViewById(R.id.id);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        factory = findViewById(R.id.factory);
        btnSave  = findViewById(R.id.btn_save);
        db = new DataBase(getApplicationContext());
        int idAcc = Integer.parseInt(getIntent().getStringExtra("id"));
        employee = db.getEmployee(idAcc);
    }

    void setEvent(){
        id.setText(employee.getId()+"");
        firstname.setText(employee.getFirstName());
        lastname.setText(employee.getLastName());
        factory.setText(employee.getFactory());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employee.setLastName(lastname.getText().toString());
                employee.setFirstName(firstname.getText().toString());
                employee.setFactory(factory.getText().toString());

                boolean result = db.editEmployee(employee);
                Toast.makeText(getApplicationContext(), result ? "Thành công" : "Thất bại", Toast.LENGTH_SHORT );
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}