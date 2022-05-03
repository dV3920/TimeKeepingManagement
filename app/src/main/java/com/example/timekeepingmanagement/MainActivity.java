package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.timekeepingmanagement.fragment.ListTimeKeepingFragment;

import com.example.timekeepingmanagement.entity.Users;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {
    Button btnProduct, btnEmployee, btnTimeKeeping, btnThongKe, btnAccount, btnProfile;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setControl();
        setEvent();

    }

    void setControl(){
        btnProduct = findViewById(R.id.btnProduct);
        btnEmployee = findViewById(R.id.btnEmployee);
        btnTimeKeeping = findViewById(R.id.btnTimeKeeping);
        btnThongKe = findViewById(R.id.btnThongKe);
        btnAccount = findViewById(R.id.btnAccount);
        btnProfile = findViewById(R.id.btnProfile);
        id = getIntent().getStringExtra("id");
    }

    void setEvent(){
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.equals("1") != true){
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Bạn không có quyền truy cập")
                            .show();
                    return;
                }
            Intent intent = new Intent(MainActivity.this, ProductActivity.class);
               startActivity(intent);

            }
        });

        btnEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.equals("1") != true){
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Bạn không có quyền truy cập")
                            .show();
                    return;
                }
               Intent intent = new Intent(MainActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });

        btnTimeKeeping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TimeKeepingActivity.class);
                startActivity(intent);
            }
        });

            btnThongKe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(id.equals("1") != true){
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Bạn không có quyền truy cập")
                                .show();
                        return;
                    }
                    startActivity(new Intent(getApplicationContext(), ThongKeActivity.class));
                }
            });

            btnAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(id.equals("1") != true){
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Bạn không có quyền truy cập")
                                .show();
                        return;
                    }
                    startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                }
            });


    }
}