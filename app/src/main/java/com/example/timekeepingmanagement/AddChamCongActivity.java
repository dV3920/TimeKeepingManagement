package com.example.timekeepingmanagement;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.os.Bundle;

import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.timekeepingmanagement.adapter.EmployeeSpnAdapter;

import com.example.timekeepingmanagement.adapter.TimeKeepingAdapter;
import com.example.timekeepingmanagement.entity.Employee;
import com.example.timekeepingmanagement.entity.TimeKeeping;

import android.widget.AdapterView.OnItemSelectedListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;



public class AddChamCongActivity extends AppCompatActivity{
    TextView date_show;
    EditText idEmployee;
    CalendarView calendarView;
    Button btnApply, btnCancel;
    DataBase db;
    Boolean isAdd = true;
    EmployeeSpnAdapter employeeAdapter;
//    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timekeeping);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isSuccess = true;
//                if(isAdd){
                try {
                    isSuccess =  db.addTimeKeeping(getTimeKeeping());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //                }
//                else {
//                    try {
//                        isSuccess =  db.addTimeKeeping(getTimeKeeping());
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }

                Toast.makeText(getApplicationContext(), isSuccess ? "Thành công":"Lỗi" , Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddChamCongActivity.this, ListChamCongActivity.class);
                startActivity(intent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setControl() {
        db = new DataBase(getApplicationContext());
        calendarView = findViewById(R.id.calendarView);
        date_show = findViewById(R.id.date_show);
        idEmployee = findViewById(R.id.idEmployee);
//        spinner = findViewById(R.id.spinner);
//        spinner.setOnItemSelectedListener(this);
        btnApply = findViewById(R.id.btnApplyTimeKeeping);
        btnCancel = findViewById(R.id.btnCancelTimeKeeping);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
                    {

                        String Date
                                = String.format("%02d",dayOfMonth) + "-"
                                + String.format("%02d", (month + 1)) + "-" + year;


                        String Date = dayOfMonth + "-" + (month + 1) + "-" + year;

                        date_show.setText(Date);
                    }
                });
        isAdd = (Boolean) getIntent().getSerializableExtra("isAdd");

//        employeeAdapter = new EmployeeSpnAdapter(this,R.layout.employee_spinner,db.readEmployees());
//        spinner.setAdapter(employeeAdapter);
    }
    TimeKeeping getTimeKeeping() throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(date_show.getText().toString());
        TimeKeeping timeKeeping = new TimeKeeping(Integer.parseInt(idEmployee.getText().toString().trim()),date);
        return timeKeeping;

        employeeAdapter = new EmployeeSpnAdapter(this,R.layout.employee_spinner,db.readEmployees());
        spinner.setAdapter(employeeAdapter);

    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        idEd = adapterView.getItemAtPosition(i).getId();
//        Toast.makeText(getApplication(), "Bạn đã chọn mục:"+ idEd, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}
