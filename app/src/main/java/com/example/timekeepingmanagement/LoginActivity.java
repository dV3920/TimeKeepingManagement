package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timekeepingmanagement.database.DataBase;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnSignUp;
    EditText txtUserName;
    EditText txtPasswd;
    DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUserName = (EditText) findViewById(R.id.txtUsername);
        txtPasswd = (EditText) findViewById(R.id.txtPassword);
        database = new DataBase(getApplicationContext());
        btnLogin = (Button) findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUserName.length() != 0 && txtPasswd.length() != 0){
                    if(database.checkLogin(txtUserName.getText().toString().trim(),txtPasswd.getText().toString().trim())){
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        i.putExtra("username",txtUserName.getText().toString());
                        startActivity(i);
                       // Toast.makeText(LoginActivity.this, "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!!!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignUpActiviry.class);
                startActivity(i);
            }
        });
    }

}