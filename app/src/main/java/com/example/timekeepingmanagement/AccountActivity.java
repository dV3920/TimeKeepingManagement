package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.timekeepingmanagement.adapter.AccountAdapter;
import com.example.timekeepingmanagement.database.DataBase;
import com.example.timekeepingmanagement.entity.Users;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    DataBase dataBase;
    ListView lvAccount;
    ArrayList<Users> arrayAccout;
    AccountAdapter accountAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        lvAccount = (ListView) findViewById(R.id.lvListAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        arrayAccout = new ArrayList<>();
        accountAdapter = new AccountAdapter(arrayAccout,this,R.layout.raw_account);
        lvAccount.setAdapter(accountAdapter);
        dataBase = new DataBase(getApplicationContext());

        String sql = "select * from Users";
        SQLiteDatabase databases = dataBase.getReadableDatabase();
        Cursor cursor = databases.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                arrayAccout.add(new Users(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        accountAdapter.notifyDataSetChanged();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}