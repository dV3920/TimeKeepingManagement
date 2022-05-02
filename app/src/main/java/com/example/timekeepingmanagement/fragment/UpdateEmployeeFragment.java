package com.example.timekeepingmanagement.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.timekeepingmanagement.DataBase;
import com.example.timekeepingmanagement.R;
import com.example.timekeepingmanagement.adapter.EmployeeSpnAdapter;
import com.example.timekeepingmanagement.entity.Employee;

import java.util.ArrayList;

public class UpdateEmployeeFragment  extends Fragment {
    View convertView;
    Spinner spinner;
    EmployeeSpnAdapter employeeAdapter;
    ArrayList<Employee> data = new ArrayList<>();
    DataBase db;
    EditText edFirstNameEmployee, edLastNameEmployee, edFactory;
    Employee currentSelect;
    Button btnApplyEmployee;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.update_employee_fragment, container, false);
        setControl();
        setEvent();
        ((AppCompatActivity)getContext()).getSupportActionBar().setTitle(R.string.update_employee_menu);
        return convertView;
    }

    void  setEvent(){
        init();
        employeeAdapter = new EmployeeSpnAdapter(getContext(),R.layout.employee_spinner,data);
        spinner.setAdapter(employeeAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setCurrentSelect(data.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        btnApplyEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCurrentItem();
                boolean result = db.editEmployee(getCurrentSelect());
                if (result){
                    Toast.makeText(getContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getContext(),"Cập nhật thất bại", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void setControl(){
        spinner = convertView.findViewById(R.id.spinner);
        db = new DataBase(getContext());
        edFirstNameEmployee = convertView.findViewById(R.id.edFirstNameEmployee);
        edLastNameEmployee = convertView.findViewById(R.id.edLastNameEmployee);
        edFactory = convertView.findViewById(R.id.edFactory);
        btnApplyEmployee = convertView.findViewById(R.id.btnApplyEmployee);
    }

    void setCurrentSelect(Employee item){
        currentSelect = item;
        edFirstNameEmployee.setText(currentSelect.getFirstName());
        edLastNameEmployee.setText(currentSelect.getLastName());
        edFactory.setText(currentSelect.getFactory());
    }

    void updateCurrentItem(){
        currentSelect.setFirstName(edFirstNameEmployee.getText().toString());
        currentSelect.setLastName(edLastNameEmployee.getText().toString());
        currentSelect.setFactory(edFactory.getText().toString());
    }

    public Employee getCurrentSelect() {
        return currentSelect;
    }

    void init(){
        data = db.readEmployees();
    }
}
