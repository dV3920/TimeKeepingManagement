package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.timekeepingmanagement.entity.Users;

public class MainActivity extends AppCompatActivity {
    Button btnProduct, btnEmployee, btnTimeKeeping, btnThongKe, btnAccount, btnProfile;
    String id;
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
                Intent intent = new Intent(MainActivity.this, ListChamCongActivity.class);
                startActivity(intent);
            }
        });


        if(id.equals("1") ) {
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