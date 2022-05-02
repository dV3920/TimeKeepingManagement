package com.example.timekeepingmanagement.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.timekeepingmanagement.DataBase;
import com.example.timekeepingmanagement.R;
import com.example.timekeepingmanagement.entity.Employee;


import java.util.ArrayList;


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



     //   ImageView ivEditEmployee= convertView.findViewById(R.id.ivEditEmployee);
        ImageView ivRemoveEmployee = convertView.findViewById(R.id.ivRemoveEmployee);

//        ivEditEmployee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, AddEmployeeActivity.class);
//                intent.putExtra("isAdd",false);
//                intent.putExtra("Object",employee);
//                context.startActivity(intent);
//            }
//        });
        ivRemoveEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder adb = new AlertDialog.Builder(context);
                adb.setMessage("Bạn có muốn xóa nhân viên có mã là "+ employee.getId());
                adb.setTitle("Thông báo");
                adb.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Boolean isSuccess =  new DataBase(context).removeEmployee(employee.getId());
                        if(isSuccess){
                            Toast.makeText(context,"Xóa thành công",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(context,"Lỗi",Toast.LENGTH_LONG).show();
                        }
                        data.remove(employee);
                        notifyDataSetChanged();
                    }
                });
                adb.setNegativeButton("Hủy",null);
                adb.show();

            }
        });

        return convertView;
    }

    public void search(String query){
        data.clear();
        query=query.trim().toLowerCase();
        for (Employee e: databk) {
            if(e.toString().contains(query)){
                data.add(e);
            }
        }

        notifyDataSetInvalidated();
    }




}
