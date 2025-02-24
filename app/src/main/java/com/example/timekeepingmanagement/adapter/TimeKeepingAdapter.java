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
import com.example.timekeepingmanagement.entity.TimeKeeping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TimeKeepingAdapter extends ArrayAdapter {
    ArrayList<TimeKeeping> data,databk;
    Context context;
    int resource;
    public TimeKeepingAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TimeKeeping> data) {
        super(context, resource, data);
        this.data=data;
        this.databk = new ArrayList<>();
        this.databk.addAll(data);
        this.context=context;
        this.resource=resource;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView id = convertView.findViewById(R.id.idMaChamCong);
        TextView idEmployee = convertView.findViewById(R.id.idNhanVien);
        TextView dateTimeKeeping = convertView.findViewById(R.id.idngay);

        TimeKeeping timeKeeping = data.get(position);
        id.setText(timeKeeping.getId()+"");
        idEmployee.setText(Integer.toString(timeKeeping.getIdEmployee()));
        dateTimeKeeping.setText(timeKeeping.getDateTimeKeeping().toString());

//        ImageView ivEditEmployee= convertView.findViewById(R.id.addChamCong);
//
//        ivEditEmployee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, AddChamCongActivity.class);
//                context.startActivity(intent);
//            }
//        });
        return convertView;
    }
    public void search(String query){
        data.clear();
        query=query.trim().toLowerCase();
        for (TimeKeeping e: databk) {
            if(e.toString().contains(query)){
                data.add(e);
            }
        }

        notifyDataSetInvalidated();
    }
}
