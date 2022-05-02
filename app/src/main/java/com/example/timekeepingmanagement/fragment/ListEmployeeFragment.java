package com.example.timekeepingmanagement.fragment;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.timekeepingmanagement.DataBase;
import com.example.timekeepingmanagement.R;
import com.example.timekeepingmanagement.adapter.EmployeeAdapter;
import com.example.timekeepingmanagement.entity.Employee;

import java.util.ArrayList;

public class ListEmployeeFragment  extends Fragment {
    ListView lvListEmployee;
    DataBase db;
    ArrayList<Employee> data = new ArrayList<>();
    EmployeeAdapter employeeAdapter;
    View convertView;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.list_employee_fragment, container, false);
        setControl();
        setEvent();
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle(R.string.list_employee_menu);
        return convertView;
    }

    void setControl(){
        db = new DataBase(this.getContext());
        lvListEmployee = convertView.findViewById(R.id.lvListEmployee);
        searchView = convertView.findViewById(R.id.search);
    }

    void setEvent(){
        init();
        employeeAdapter = new EmployeeAdapter(this.getContext(),R.layout.raw_employee,data);
        lvListEmployee.setAdapter(employeeAdapter);
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
