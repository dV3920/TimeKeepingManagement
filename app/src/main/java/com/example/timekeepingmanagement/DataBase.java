package com.example.timekeepingmanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.timekeepingmanagement.entity.Employee;
import com.example.timekeepingmanagement.entity.Product;
import com.example.timekeepingmanagement.entity.TimeKeeping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context) {
        super(context, "TimeKeepingDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table Employee (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "firstName text NOT NULL, " +
                        "lastName text NOT NULL, " +
                        "factory text NOT NULL)";
        sqLiteDatabase.execSQL(sql);
        sql = "Create table Product (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name text NOT NULL, " +
                "price float NOT NULL," +
                "image BLOB)";
        sqLiteDatabase.execSQL(sql);
        sql = "Create table TimeKeeping(\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idEmployee int, " +
                "dateTimeKeeping datetime, " +
                "FOREIGN KEY(idEmployee) REFERENCES Employee(id))" ;
        sqLiteDatabase.execSQL(sql);
        sql ="Create table InfoTimeKeeping(\n" +
                "idTime INTEGER, " +
                "idProduct INTEGER, " +
                "num1Pro int NOT NULL, " +
                "num0Pro int NOT NULL, " +
                "PRIMARY KEY(idTime,idProduct),\n" +
                "FOREIGN KEY(idTime) REFERENCES TimeKeeping(id)," +
                "FOREIGN KEY(idProduct) REFERENCES Product(id))"; // num1Pro: Số thành phẩm, num0Pro: số phế phẩm
        sqLiteDatabase.execSQL(sql);

        sqLiteDatabase.execSQL("INSERT INTO Employee values(?,?,?,?)",new String[]{"1","Nguyễn","Văn A","A"});
        sqLiteDatabase.execSQL("INSERT INTO Product values(?,?,?)",new String[]{"1","Sắt","1000", null});
        sqLiteDatabase.execSQL("INSERT INTO TimeKeeping values(?,?,datetime('now'))",new String[]{"1","1"});
        sqLiteDatabase.execSQL("INSERT INTO InfoTimeKeeping values(?,?,?,?)",new String[]{"1","0","10","1"});
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
        cursor.close();
        return data;
    }

    public Boolean addProduct(Product product){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("INSERT INTO Product(name, price) values(?,?)",new String[]{product.getName(), product.getPrice()+""});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean editProduct(Product product){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("Update Product set name=?,price=? where id=?",new String[]{product.getName(), product.getPrice()+"", product.getId()+""});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean removeProduct(Product product){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("Delete From Product where id=?",new String[]{product.getId()+""});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean removeProduct(int id){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("Delete From Product where id=?",new Integer[]{id});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean removeEmployee(int id){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("Delete From Employee where id=?",new Integer[]{id});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Employee> readEmployees(){
        ArrayList<Employee> data = new ArrayList<>();
        String sql = "select * from employee";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                data.add(new Employee(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public Boolean addEmpoyee(Employee employee){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("INSERT INTO Employee(firstName, lastName, factory) values(?,?,?)",new String[]{employee.getFirstName(), employee.getLastName(), employee.getFactory()});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean editEmployee(Employee employee){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("Update Employee set firstName=?,lastName=?,factory=? where id=?",new String[]{employee.getFirstName(), employee.getLastName(), employee.getFactory(),employee.getId()+""});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<TimeKeeping> readTimeKeeping() throws ParseException {
        ArrayList<TimeKeeping> data = new ArrayList<>();
        String sql = "select * from timekeeping";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                        Locale.ENGLISH);
                data.add(new TimeKeeping(cursor.getInt(0), cursor.getInt(1), format.parse(cursor.getString(2))));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
}
