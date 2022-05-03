package com.example.timekeepingmanagement.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.timekeepingmanagement.database.DataBase;
import com.example.timekeepingmanagement.R;
import com.example.timekeepingmanagement.entity.Employee;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class EmployeePlusFragment extends Fragment {
    View convertView;
    EditText edFirstName, edLastName, edFactory;
    Button btnApply;
    DataBase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.add_employee_fragment, container, false);
        setControl();
        setEvent();
        ((AppCompatActivity)getContext()).getSupportActionBar().setTitle(R.string.add_employee_menu);
        return convertView;
    }

    void setControl(){
        edFirstName = convertView.findViewById(R.id.edFirstNameEmployee);
        edLastName = convertView.findViewById(R.id.edLastNameEmployee);
        edFactory = convertView.findViewById(R.id.edFactory);
        btnApply = convertView.findViewById(R.id.btnApplyEmployee);
        db = new DataBase(getContext());
    }

    void setEvent(){
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isSuccess= db.addEmpoyee(getEmployee());
                new SweetAlertDialog(getContext(), isSuccess ? SweetAlertDialog.SUCCESS_TYPE : SweetAlertDialog.ERROR_TYPE)
                        .setTitleText( isSuccess ? "Thành công" : "Thất bại")
                        .show();
                if(isSuccess){
                    edFirstName.setText("");
                    edLastName.setText("");
                    edFactory.setText("");
                }
            }
        });
    }
    Employee getEmployee(){
        String firstName = edFirstName.getText().toString(),
                lastName = edLastName.getText().toString(),
                factory = edFactory.getText().toString();

        return new Employee(0,firstName,lastName,factory);
    }
}
