package com.example.timekeepingmanagement.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.timekeepingmanagement.database.DataBase;
import com.example.timekeepingmanagement.R;
import com.example.timekeepingmanagement.entity.Employee;


import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class EmployeeAdapter extends ArrayAdapter {
    ArrayList<Employee> data, databk;
    Context context;
    int resource;


    public EmployeeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Employee> data) {
        super(context, resource, data);
        this.data = data;
        this.databk = new ArrayList<>();
        this.databk.addAll(data);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvIdEmployee = convertView.findViewById(R.id.tvIdEmployee);
        TextView tvFirstName = convertView.findViewById(R.id.tvFistNameEmployee);
        TextView tvLastName = convertView.findViewById(R.id.tvLastNameEmployee);
        TextView tvFactory = convertView.findViewById(R.id.tvFactory);

        Employee employee = data.get(position);
        tvIdEmployee.setText(employee.getId()+" ");
        tvFirstName.setText(employee.getFirstName());
        tvLastName.setText(employee.getLastName());
        tvFactory.setText(employee.getFactory());

        ImageView ivRemoveEmployee = convertView.findViewById(R.id.ivRemoveEmployee);

        ivRemoveEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Cảnh báo")
                        .setContentText("Bạn có chắn sẽ xóa nhân viên có mã là "+ employee.getId() + " - Họ tên: "+employee.getFirstName() + " "+employee.getLastName())
                        .setConfirmText("Có")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                Boolean isSuccess =  new DataBase(context).removeEmployee(employee.getId());
                                if(isSuccess){
                                    data.remove(employee);
                                    notifyDataSetChanged();
                                }
                                    new SweetAlertDialog(getContext(), isSuccess ? SweetAlertDialog.SUCCESS_TYPE : SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText( isSuccess ? "Thành công" : "Thất bại")
                                            .show();

                                sDialog.dismissWithAnimation();
                            }
                        })
                        .setCancelButton("Không", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();

            }
        });

        return convertView;
    }

    public void search(String query){
        data.clear();
        query = query.trim().toLowerCase();
        for (Employee e: databk) {
            if(e.toString().contains(query)){
                data.add(e);
            }
        }
        notifyDataSetInvalidated();
    }
}
