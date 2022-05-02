package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.timekeepingmanagement.adapter.TimeKeepingAdapter;
import com.example.timekeepingmanagement.entity.TimeKeeping;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;

public class ListChamCongActivity extends AppCompatActivity {
    ListView lvListChamCong;
    DataBase db;
    ArrayList<TimeKeeping> data = new ArrayList<>();
    TimeKeepingAdapter timeKeepingAdapter;
    FloatingActionButton btnAdd;
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
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListChamCongActivity.this, AddChamCongActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setControl() {
        db = new DataBase(getApplicationContext());
        lvListChamCong = findViewById(R.id.lvListChamCong);
        btnAdd = findViewById(R.id.addChamCong);
    }
    void init() throws ParseException {
        data.addAll(db.readTimeKeeping());
    }
}