package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {

    EditText edId, edName, edPrice;
    Button btnApply, btnCancel;
    DataBase db;
    Boolean isAdd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setControl();
        setEvent();
    }

    void setControl(){
        edId = findViewById(R.id.edIdProduct);
        edName = findViewById(R.id.edNameProduct);
        edPrice = findViewById(R.id.edPriceProduct);
        btnApply = findViewById(R.id.btnApplyProduct);
        btnCancel = findViewById(R.id.btnCancelProduct);
        db = new DataBase(getApplicationContext());
        isAdd = (Boolean) getIntent().getSerializableExtra("isAdd");

    }

    void setEvent(){
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isSuccess =  db.addProduct(getProduct());
                if(isSuccess){
                    Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(AddProductActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    Product getProduct(){
        return new Product(0,edName.getText().toString(),Integer.parseInt(edPrice.getText().toString()));
    }

}