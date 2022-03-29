package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    ListView lvListProduct;
    DataBase db;
    ArrayList<Product> data = new ArrayList<>();
    ProductAdapter productAdapter;
    ImageView ivAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setControl();
        setEvent();
    }

    void  setControl(){
        db = new DataBase(getApplicationContext());
        lvListProduct = findViewById(R.id.lvListProduct);
        ivAddProduct = findViewById(R.id.ivAddProduct);
    }

    void setEvent(){
        init();
        productAdapter = new ProductAdapter(this,R.layout.raw_product,data);
        lvListProduct.setAdapter(productAdapter);
        ivAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, AddProductActivity.class);
                intent.putExtra("isAdd",true);
                startActivity(intent);
            }
        });
        lvListProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = data.get(i);
                Intent intent = new Intent(ProductActivity.this, AddProductActivity.class);
                intent.putExtra("isAdd",false);
                intent.putExtra("Object",product);
                startActivity(intent);
            }
        });


    }

    void init(){
        data.addAll(db.readProducts());
    }



}