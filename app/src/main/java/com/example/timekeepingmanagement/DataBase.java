package com.example.timekeepingmanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context) {
        super(context, "TimeKeepingDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table Employee (" +
                        "id int NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "firstName text NOT NULL, " +
                        "lastName text NOT NULL, " +
                        "factory text NOT NULL" +
                        ")";
        sqLiteDatabase.execSQL(sql);
        sql =   "Create table Product (" +
                "id int NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "name text NOT NULL, " +
                "price float NOT NULL)" ;
        sqLiteDatabase.execSQL(sql);
        sql =   "Create table TimeKeeping(\n" +
                "id int NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "idEmployee int, " +
                "dateTimeKeeping datetime, " +
                "FOREIGN KEY(idEmployee) REFERENCES Employee(id)" +
                ")" ;
        sqLiteDatabase.execSQL(sql);
        sql =   "Create table InfoTimeKeeping(\n" +
                "idTime int NOT NULL, " +
                "idProduct int NOT NULL, " +
                "num1Pro int NOT NULL, " +
                "num0Pro int NOT NULL, " +
                "PRIMARY KEY(idTime,idProduct),\n" +
                "FOREIGN KEY(idTime) REFERENCES TimeKeeping(id)," +
                "FOREIGN KEY(idProduct) REFERENCES Product(id)" +
                ")"; // num1Pro: Số thành phẩm, num0Pro: số phế phẩm
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<Product> readProducts(){
        ArrayList<Product> data = new ArrayList<>();
        String sql = "select * from product";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                data.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getFloat(2)));
            }while (cursor.moveToNext());
        }
        return data;
    }
}
