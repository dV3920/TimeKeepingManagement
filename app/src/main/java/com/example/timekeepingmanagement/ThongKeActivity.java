package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.timekeepingmanagement.database.DataBase;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ThongKeActivity extends AppCompatActivity {
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        PieChart pieChart = findViewById(R.id.pieChart);
        dataBase = new DataBase(getApplicationContext());
        ArrayList<String> arr = new ArrayList<>();
        arr = dataBase.getPhanXuong();

        ArrayList<PieEntry> visitors = new ArrayList<>();
        for(int i =0;i<arr.size();i++){
            visitors.add(new PieEntry(dataBase.getSoLuongCongNhan(arr.get(i)),arr.get(i)));
        }

        PieDataSet pieDataSet = new PieDataSet(visitors,"Nhân viên mỗi xưởng");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueLineColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        ValueFormatter vf = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ""+(int)value+" người";
            }
        };
        pieData.setValueFormatter(vf);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Nhân viên mỗi xưởng");
        pieChart.animate();
    }
}