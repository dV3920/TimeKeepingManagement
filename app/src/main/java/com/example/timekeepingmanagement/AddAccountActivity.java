package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.timekeepingmanagement.database.DataBase;
import com.example.timekeepingmanagement.entity.Users;

public class AddAccountActivity extends AppCompatActivity {
    TextView header;
    EditText username,passwd,idEmployee,id;
    Button btnApp, btnCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        DataBase dataBase;
        dataBase = new DataBase(getApplicationContext());
        header = (TextView) findViewById(R.id.tvHeaderAddAccount);
        username = (EditText) findViewById(R.id.edUsername);
        passwd = (EditText) findViewById(R.id.edPasswd);
        idEmployee = (EditText) findViewById(R.id.edAddIDEmployee);
        id = (EditText) findViewById(R.id.edIdAccount);
        btnApp = (Button) findViewById(R.id.btnApplyAccount);
        btnCancle = (Button) findViewById(R.id.btnCancelAccount);


        Intent i = getIntent();
        String flag = i.getStringExtra("flag");
        String r_id = i.getStringExtra("id");
        String r_username = i.getStringExtra("username");
        String r_passwd = i.getStringExtra("passwd");
        String r_idEmployee = i.getStringExtra("idEmployee");
        String r_id_remove = i.getStringExtra("id_remove");


        if(flag.equals("edit")){
            header.setText("Sửa thông tin tài khoản!!!");
            username.setText(r_username);
            passwd.setText(r_passwd);
            idEmployee.setText(r_idEmployee);
            id.setText(r_id);
            btnApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Boolean isSuccess =  dataBase.editAccount(getUsers());
                    if(isSuccess){
                        Toast.makeText(AddAccountActivity.this, "Sửa thông tin thành công", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AddAccountActivity.this, AccountActivity.class);
                        startActivity(i);
                    }
                }
            });
            btnCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AddAccountActivity.this, AccountActivity.class);
                    startActivity(i);
                }
            });
        }else if(flag.equals("delete")){
            AlertDialog.Builder adb = new AlertDialog.Builder(AddAccountActivity.this);
            adb.setMessage("Bạn có muốn xóa tài khoản mã là "+ r_id_remove);
            adb.setTitle("Thông báo");
            adb.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Boolean isSuccess =  new DataBase(AddAccountActivity.this).removeAccount(Integer.parseInt(r_id_remove));
                    if(isSuccess){
                        Toast.makeText(AddAccountActivity.this,"Xóa thành công",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AddAccountActivity.this, AccountActivity.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(AddAccountActivity.this,"Xóa thất bại",Toast.LENGTH_LONG).show();
                    }
                }
            });
            adb.setNegativeButton("Hủy",null);
            adb.show();
        }
    }



    Users getUsers(){
        String aId = id.getText().toString(),
                aUsername = username.getText().toString(),
                aPasswd = passwd.getText().toString(),
                aIdEmployee = idEmployee.getText().toString();



        return new Users(Integer.valueOf(aId),Integer.valueOf(aIdEmployee),aUsername,aPasswd);
    }
}