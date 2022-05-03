package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.timekeepingmanagement.database.DataBase;
import com.example.timekeepingmanagement.entity.Employee;
import com.example.timekeepingmanagement.entity.Users;
import com.example.timekeepingmanagement.fragment.ListEmployeeFragment;

import java.io.ByteArrayOutputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProfileActivity extends AppCompatActivity {
    EditText id, firstname, lastname, factory, username, password;
    Button btnSave, btnChangePassword, btnChangeAvt;
    Employee employee;
    Users user;
    DataBase db;
    ImageView avt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setControl();
        setEvent();

    }

    void setControl(){
        id = findViewById(R.id.id);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        factory = findViewById(R.id.factory);
        btnSave  = findViewById(R.id.btn_save);
        db = new DataBase(getApplicationContext());
        int idAcc = Integer.parseInt(getIntent().getStringExtra("id"));
        employee = db.getEmployee(idAcc);
        user = db.getUser(idAcc);
        btnChangePassword = findViewById(R.id.btn_change_password);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        avt = findViewById(R.id.avatar);
        btnChangeAvt = findViewById(R.id.btn_change_avt);
    }

    void setEvent(){
        username.setText(user.getUsername());
        password.setText(user.getPasswd());
        id.setText(employee.getId()+"");
        firstname.setText(employee.getFirstName());
        lastname.setText(employee.getLastName());
        factory.setText(employee.getFactory());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        avt.setImageBitmap(db.getImage(employee.getId()));
        getSupportActionBar().setTitle(R.string.profile);
        btnChangeAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employee.setLastName(lastname.getText().toString());
                employee.setFirstName(firstname.getText().toString());
                employee.setFactory(factory.getText().toString());

                boolean isSuccess = db.editEmployee(employee);
                new SweetAlertDialog(ProfileActivity.this, isSuccess ? SweetAlertDialog.SUCCESS_TYPE : SweetAlertDialog.ERROR_TYPE)
                        .setTitleText( isSuccess ? "Thành công" : "Thất bại")
                        .show();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setPasswd(password.getText().toString());
                boolean isSuccess = db.editAccount(user);
                new SweetAlertDialog(ProfileActivity.this, isSuccess ? SweetAlertDialog.SUCCESS_TYPE : SweetAlertDialog.ERROR_TYPE)
                        .setTitleText( isSuccess ? "Thành công" : "Thất bại")
                        .show();
            }
        });
    }


    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    avt.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    avt.setImageURI(selectedImage);
                    BitmapDrawable drawable = (BitmapDrawable) avt.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    byte[] data = getBitmapAsByteArray(bitmap);
                    boolean isSuccess = db.storedImage(employee.getId(),data);
                    new SweetAlertDialog(ProfileActivity.this, isSuccess ? SweetAlertDialog.SUCCESS_TYPE : SweetAlertDialog.ERROR_TYPE)
                            .setTitleText( isSuccess ? "Cập nhật hình ảnh thành công" : "Cập nhật hình ảnh thất bại")
                            .show();
                }
                break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}