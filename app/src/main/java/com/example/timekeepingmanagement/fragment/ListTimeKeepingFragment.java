package com.example.timekeepingmanagement.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.timekeepingmanagement.DataBase;
import com.example.timekeepingmanagement.R;
import com.example.timekeepingmanagement.adapter.TimeKeepingAdapter;
import com.example.timekeepingmanagement.entity.TimeKeeping;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;

public class ListTimeKeepingFragment extends Fragment {
    ListView lvListChamCong;
    DataBase db;
    ArrayList<TimeKeeping> data = new ArrayList<>();
    TimeKeepingAdapter timeKeepingAdapter;
    FloatingActionButton btnAdd;
    View convertView;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.list_cham_cong, container, false);
        setControl();
        try {
            setEvent();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle(R.string.list_timekeeping_menu);
        return convertView;
    }

    private void setEvent() throws ParseException {
        init();
        timeKeepingAdapter = new TimeKeepingAdapter(this.getContext(),R.layout.raw_cham_cong,data);
        lvListChamCong.setAdapter(timeKeepingAdapter);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ListTimeKeepingFragment.this, TimeKeepingPlusFragment.class);
//                startActivity(intent);
//            }
//        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                timeKeepingAdapter.search(query);
                return false;
            }
        });

    }

    private void setControl() {
        db = new DataBase(this.getContext());
        lvListChamCong = convertView.findViewById(R.id.lvListChamCong);
        btnAdd = convertView.findViewById(R.id.addChamCong);
        searchView = convertView.findViewById(R.id.search);
    }
    void init() throws ParseException {
        data.addAll(db.readTimeKeeping());
    }
}