package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.timekeepingmanagement.adapter.ProductAdapter;
import com.example.timekeepingmanagement.database.DataBase;
import com.example.timekeepingmanagement.entity.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    ListView lvListProduct;
    DataBase db;
    ArrayList<Product> data = new ArrayList<>();
    ProductAdapter productAdapter;
    FloatingActionButton btnAddProduct;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setTitle(R.string.name_layout_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setControl();
        setEvent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    void  setControl(){
        db = new DataBase(getApplicationContext());
        lvListProduct = findViewById(R.id.lvListProduct);
        btnAddProduct = findViewById(R.id.floatingActionButton2);
        searchView = findViewById(R.id.searchProduct);
    }

    void setEvent(){
        init();
        productAdapter = new ProductAdapter(this,R.layout.raw_product,data);
        lvListProduct.setAdapter(productAdapter);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, AddProductActivity.class);
                intent.putExtra("isAdd",true);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                productAdapter.search(query);
                return false;
            }
        });
    }

    void init(){
        data.addAll(db.readProducts());
    }



}