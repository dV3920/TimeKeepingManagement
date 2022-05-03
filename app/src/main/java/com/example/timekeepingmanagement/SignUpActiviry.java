package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timekeepingmanagement.database.DataBase;
import com.example.timekeepingmanagement.entity.Users;

public class SignUpActiviry extends AppCompatActivity {
    EditText edUsername;
    EditText edPasswd;
    EditText edIdEmployee;
    Button btnSignUp;
    Button btnSignIn;
    DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activiry);
        edUsername = (EditText) findViewById(R.id.edSignUp_Username);
        edPasswd = (EditText) findViewById(R.id.edSignUp_Passwd);
        edIdEmployee = (EditText) findViewById(R.id.edSignUp_IdEmployee);
        btnSignIn = (Button) findViewById(R.id.btnSignUp_Login);
        btnSignUp = (Button) findViewById(R.id.btnSignUp_SignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DataBase(getApplicationContext());
                Users users2 = getUsers();
                if(db.checkLogin(users2.getUsername().toString().trim(),users2.getPasswd().toString().trim()) == false){
                    boolean a = db.addUsers(getUsers());
                    if(a){
                        Intent i = new Intent(SignUpActiviry.this,LoginActivity.class);
                        startActivity(i);
                        Toast.makeText(SignUpActiviry.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SignUpActiviry.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUpActiviry.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActiviry.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
    Users getUsers(){

        String username = edUsername.getText().toString(),
                passwd = edPasswd.getText().toString(),
                idEmployee = edIdEmployee.getText().toString();
        return new Users(0,username,passwd,Integer.parseInt(idEmployee));
    }
}