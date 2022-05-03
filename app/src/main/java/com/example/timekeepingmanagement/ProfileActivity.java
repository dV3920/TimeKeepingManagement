package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
      //  Toast.makeText(this,getIntent().getStringExtra("username"),Toast.LENGTH_SHORT).show();
    }
}