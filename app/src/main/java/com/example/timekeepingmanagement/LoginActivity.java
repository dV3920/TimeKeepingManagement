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
                String username = txtUserName.getText().toString().trim(),
                        password = txtPasswd.getText().toString().trim();


                if(txtUserName.length() != 0 && txtPasswd.length() != 0){
                    int result = database.checkLogin(username,password);
                    if(result != -1 ){
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        i.putExtra("id",result+"");
                        startActivity(i);
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