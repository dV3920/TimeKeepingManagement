package com.example.timekeepingmanagement;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.timekeepingmanagement.adapter.EmployeeAdapter;
import com.example.timekeepingmanagement.adapter.TimeKeepingAdapter;
import com.example.timekeepingmanagement.entity.Employee;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;


public class AddChamCongActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView date_show;
    CalendarView calendarView;
    DataBase db;
    Boolean isAdd = true;
    ArrayList<Employee> data = new ArrayList<>();
    EmployeeAdapter employeeAdapter;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timekeeping);
        setControl();
        setEvent();
    }

    private void setEvent() {

    }

    private void setControl() {
        calendarView = findViewById(R.id.calendarView);
        date_show = findViewById(R.id.date_show);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(
                            @NonNull CalendarView view,
                            int year,
                            int month,
                            int dayOfMonth)
                    {
                        String Date
                                = dayOfMonth + "-"
                                + (month + 1) + "-" + year;

                        date_show.setText(Date);
                    }
                });
        db = new DataBase(getApplicationContext());
        isAdd = (Boolean) getIntent().getSerializableExtra("isAdd");
        employeeAdapter = new EmployeeAdapter(this,R.layout.activity_timekeeping,data);

        employeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(employeeAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Employee employee = (Employee) adapterView.getSelectedItem();
//        Toast.makeText(getApplicationContext(), "Selected Employee: " +  employee.getLastName() ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
