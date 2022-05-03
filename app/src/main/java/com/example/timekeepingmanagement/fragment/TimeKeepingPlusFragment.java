package com.example.timekeepingmanagement.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.timekeepingmanagement.R;

import com.example.timekeepingmanagement.database.DataBase;
import com.example.timekeepingmanagement.entity.TimeKeeping;

import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeKeepingPlusFragment extends Fragment {
    TextView date_show;
    EditText idEmployee;
    CalendarView calendarView;
    DataBase db;
    Button btnApply, btnCancel;
//    Boolean isAdd = true;
    View convertView;
    //    EmployeeSpnAdapter employeeAdapter;
    //    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        convertView = inflater.inflate(R.layout.add_timekeeping, container, false);
        ((AppCompatActivity)getContext()).getSupportActionBar().setTitle(R.string.add_timekeeping_menu);
        setControl();
        setEvent();
        return convertView;
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


                Toast.makeText(getContext(), isSuccess ? "Thành công":"Lỗi" , Toast.LENGTH_LONG).show();
                if(isSuccess){
                    date_show.setText("");
                    idEmployee.setText("");
                }
            }
        });
    }

    private void setControl() {
        db = new DataBase(getContext());
        calendarView = convertView.findViewById(R.id.calendarView);
        date_show = convertView.findViewById(R.id.date_show);
        idEmployee = convertView.findViewById(R.id.idEmployee);
//        spinner = convertView.findViewById(R.id.spinner);
//        spinner.setOnItemSelectedListener(this);
        btnApply = convertView.findViewById(R.id.btnApplyTimeKeeping);
        btnCancel = convertView.findViewById(R.id.btnCancelTimeKeeping);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {

                String Date
                        = String.format("%02d",dayOfMonth) + "-"
                        + String.format("%02d", (month + 1)) + "-" + year;
                date_show.setText(Date);
            }
        });

    }
    TimeKeeping getTimeKeeping() throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(date_show.getText().toString());
        TimeKeeping timeKeeping = new TimeKeeping(Integer.parseInt(idEmployee.getText().toString().trim()),date);
        return timeKeeping;

    }

}
