package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.timekeepingmanagement.adapter.EmployeeAdapter;
import com.example.timekeepingmanagement.adapter.TimeKeepingAdapter;
import com.example.timekeepingmanagement.entity.Employee;
import com.example.timekeepingmanagement.entity.TimeKeeping;

import java.text.ParseException;
import java.util.ArrayList;

public class ListChamCongActivity extends AppCompatActivity {
    ListView lvListChamCong;
    SearchView inputSearch;
    DataBase db;
    ArrayList<TimeKeeping> data = new ArrayList<>();
    TimeKeepingAdapter timeKeepingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_cham_cong);
        setControl();
        try {
            setEvent();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void setEvent() throws ParseException {
        init();
        timeKeepingAdapter = new TimeKeepingAdapter(this,R.layout.raw_cham_cong,data);
        lvListChamCong.setAdapter(timeKeepingAdapter);
    }

    private void setControl() {
        db = new DataBase(getApplicationContext());
        lvListChamCong = findViewById(R.id.lvListChamCong);
    }
    void init() throws ParseException {
        data.addAll(db.readTimeKeeping());
    }
}