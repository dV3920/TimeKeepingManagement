package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timekeepingmanagement.database.DataBase;
import com.example.timekeepingmanagement.entity.Product;

public class AddProductActivity extends AppCompatActivity {

    EditText edId, edName, edPrice;
    Button btnApply, btnCancel;
    DataBase db;
    Boolean isAdd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getSupportActionBar().setTitle(R.string.name_layout_add_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setControl();
        setEvent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    void setControl(){
        edId = findViewById(R.id.edIdProduct);
        edName = findViewById(R.id.edNameProduct);
        edPrice = findViewById(R.id.edPriceProduct);
        btnApply = findViewById(R.id.btnApplyProduct);
        btnCancel = findViewById(R.id.btnCancelProduct);
        db = new DataBase(getApplicationContext());
        isAdd = (Boolean) getIntent().getSerializableExtra("isAdd");
        if(isAdd == false){
            Product product = (Product) getIntent().getSerializableExtra("Object");
            edId.setText(product.getId()+"");
            edName.setText(product.getName());
            edPrice.setText(product.getPrice()+"");
            getSupportActionBar().setTitle(R.string.name_layout_edit_product);
        }

    }

    void setEvent(){
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAdd){
                    Boolean isSuccess =  db.addProduct(getProduct());
                    if(isSuccess){
                        Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_LONG).show();
                    }
                    Intent intent = new Intent(AddProductActivity.this, ProductActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Boolean isSuccess =  db.editProduct(getProduct());
                    if(isSuccess){
                        Toast.makeText(getApplicationContext(),"Thành công",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_LONG).show();
                    }
                    Intent intent = new Intent(AddProductActivity.this, ProductActivity.class);
                    startActivity(intent);
                }

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
        int id;
        String ID = edId.getText().toString();
        if(ID.equals("")){
            id = 0;
        }
        else{
            id = Integer.parseInt(ID);
        }

        return new Product(id,edName.getText().toString(),Float.parseFloat(edPrice.getText().toString()));
    }

}