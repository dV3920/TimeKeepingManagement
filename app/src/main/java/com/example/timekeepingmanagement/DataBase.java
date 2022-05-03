package com.example.timekeepingmanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.timekeepingmanagement.entity.Employee;
import com.example.timekeepingmanagement.entity.Product;
import com.example.timekeepingmanagement.entity.TimeKeeping;
import com.example.timekeepingmanagement.entity.Users;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context) {
        super(context, "TimeKeepingDb", null, 2);
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
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "name text NOT NULL, " +
                "price float NOT NULL)" ;
        sqLiteDatabase.execSQL(sql);
        sql = "Create table TimeKeeping(\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idEmployee int, " +
                "dateTimeKeeping datetime, " +
                "FOREIGN KEY(idEmployee) REFERENCES Employee(id))" ;
        sqLiteDatabase.execSQL(sql);
        sql ="Create table InfoTimeKeeping(\n" +
                "idTime INTEGER , " +
                "idProduct INTEGER, " +
                "num1Pro int NOT NULL, " +
                "num0Pro int NOT NULL, " +
                "PRIMARY KEY(idTime,idProduct),\n" +
                "FOREIGN KEY(idTime) REFERENCES TimeKeeping(id)," +
                "FOREIGN KEY(idProduct) REFERENCES Product(id))"; // num1Pro: Số thành phẩm, num0Pro: số phế phẩm
        sqLiteDatabase.execSQL(sql);
        sql ="Create table Users(\n" +
                "id INTEGER , " +
                "idEmployee INTEGER, username text, passwd text, "+
                "FOREIGN KEY(idEmployee) REFERENCES Employee(id))";
        sqLiteDatabase.execSQL(sql);

        sqLiteDatabase.execSQL("INSERT INTO Employee values(?,?,?,?)",new String[]{"1","Nguyễn","Văn A","A"});
        sqLiteDatabase.execSQL("INSERT INTO Users values(?,?,?,?)",new String[]{"1","1","admin","admin"});

        sqLiteDatabase.execSQL("INSERT INTO Product values(?,?,?)",new String[]{"1","Sắt","1000"});
        sqLiteDatabase.execSQL("INSERT INTO TimeKeeping values(?,?,?)",new String[]{"0","1", "Wed Oct 15 00:00:00 GMT+05:30 2008"});

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
                DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",
                        Locale.ENGLISH);
                data.add(new TimeKeeping(cursor.getInt(0), cursor.getInt(1), format.parse(cursor.getString(2))));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public Boolean addTimeKeeping(TimeKeeping timeKeeping){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("INSERT INTO TimeKeeping(idEmployee, dateTimeKeeping) values(?,?)",new String[]{timeKeeping.getIdEmployee()+"", timeKeeping.getDateTimeKeeping().toString()});

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public Boolean editTimeKeeping(TimeKeeping timeKeeping){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("Update TimeKeeping set idEmployee=?,dateTimeKeeping=? where id=?",new String[]{timeKeeping.getIdEmployee()+"", timeKeeping.getDateTimeKeeping()+"",timeKeeping.getId()+""});

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean removeAccount(int id){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("Delete From Users where id=?",new Integer[]{id});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean editAccount(Users users){
        try{
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL("Update Users set username=?,passwd=?,idEmployee=? where id=?",new String[]{
                    users.getUsername(), users.getPasswd(),users.getIdEmployee()+"",users.getId()+""
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean checkLogin(String username, String passwd){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select * from users where username='"+username+"' and passwd='"+passwd+"'";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }

    }

    public Boolean addUsers(Users users){
        SQLiteDatabase database = getWritableDatabase();
        try{
            //database.execSQL("INSERT INTO Users(id, username, passwd, idEmployee) values(?,?,?,?)",new String[]{null, users.getUsername(), users.getPasswd(), users.getIdEmployee()+""});
            String sql = "INSERT INTO Users(id, idEmployee, username, passwd) values(null,'"+users.getIdEmployee()+"','"+users.getUsername()+"','"+users.getPasswd()+"')";
            database.execSQL(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //get phan xuong
    public ArrayList<String> getPhanXuong(){
        ArrayList<String> data = new ArrayList<>();
        String sql = "select DISTINCT factory from employee";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                data.add(new String(cursor.getString(0)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public int getSoLuongCongNhan(String factory){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select * from employee where factory='"+factory+"'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }
}
