package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.timekeepingmanagement.fragment.ListTimeKeepingFragment;

import com.example.timekeepingmanagement.entity.Users;

public class MainActivity extends AppCompatActivity {
    Button btnProduct, btnEmployee, btnTimeKeeping, btnThongKe, btnAccount;
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();

    }

    void setControl(){
        btnProduct = findViewById(R.id.btnProduct);
        btnEmployee = findViewById(R.id.btnEmployee);
        btnTimeKeeping = findViewById(R.id.btnTimeKeeping);
        btnThongKe = findViewById(R.id.btnThongKe);
        btnAccount = findViewById(R.id.btnAccount);
    }

    void setEvent(){
        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ProductActivity.class);
               startActivity(intent);

            }
        });
        btnEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        Intent i = getIntent();
        String username = i.getStringExtra("username");
        if(username.equals("admin")) {
            btnThongKe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), ThongKeActivity.class));
                }
            });

            btnAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                }
            });

        }else{
            btnThongKe.setVisibility(View.INVISIBLE);
            btnAccount.setVisibility(View.INVISIBLE);
        }
    }
}