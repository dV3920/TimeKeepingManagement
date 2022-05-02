package com.example.timekeepingmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.timekeepingmanagement.R;
import com.example.timekeepingmanagement.entity.Employee;

import java.util.ArrayList;

public class EmployeeSpnAdapter  extends ArrayAdapter {
    Context context;
    ArrayList<Employee> data;
    int resource;

    public EmployeeSpnAdapter(Context context, int resource, ArrayList<Employee> data)
    {
        super(context, resource, data);
        this.data = data;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent)
    {

        convertView = LayoutInflater.from(context).inflate(resource, null);


        TextView textViewName = convertView.findViewById(R.id.spn_tv_name);
        TextView textViewId = convertView.findViewById(R.id.spn_tv_id);
        Employee currentItem = (Employee) getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (currentItem != null) {
            textViewName.setText(currentItem.getFirstName()+" "+currentItem.getLastName());
            textViewId.setText(currentItem.getId()+" - ");
        }
        return convertView;
    }
    public String getId(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView textViewId = convertView.findViewById(R.id.spn_tv_id);
        return textViewId.toString();
    }
}
