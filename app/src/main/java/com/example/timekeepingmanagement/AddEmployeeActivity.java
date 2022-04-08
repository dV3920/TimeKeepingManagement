package com.example.timekeepingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timekeepingmanagement.entity.Employee;

public class AddEmployeeActivity extends AppCompatActivity {

    EditText edId, edFirstName, edLastName, edFactory;
    Button btnApply, btnCancel;
    DataBase db;
    Boolean isAdd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        setControl();
        setEvent();
    }

    void setControl(){
        edId = findViewById(R.id.edIdEmployee);
        edFirstName = findViewById(R.id.edFirstNameEmployee);
        edLastName = findViewById(R.id.edLastNameEmployee);
        edFactory = findViewById(R.id.edFactory);
        btnApply = findViewById(R.id.btnApplyEmployee);
        btnCancel = findViewById(R.id.btnCancelEmployee);
        db = new DataBase(getApplicationContext());
        isAdd = (Boolean) getIntent().getSerializableExtra("isAdd");
        TextView tvHeaderAddEmployee = findViewById(R.id.tvHeaderAddEmployee);
        if(isAdd == false){
            Employee employee = (Employee) getIntent().getSerializableExtra("Object");
            edId.setText(employee.getId()+"");
            edFirstName.setText(employee.getFirstName());
            edLastName.setText(employee.getLastName());
            edFactory.setText(employee.getFactory());
            tvHeaderAddEmployee.setText("Thông Tin Nhân Viên");
        }

    }

    void setEvent(){
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isSuccess;
                if(isAdd){
                     isSuccess =  db.addEmpoyee(getEmployee());
                }
                else {
                    isSuccess =  db.editEmployee(getEmployee());
                }

                Toast.makeText(getApplicationContext(), isSuccess ? "Thành công":"Lỗi" , Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddEmployeeActivity.this, EmployeeActivity.class);
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
    Employee getEmployee(){
        int id;
        String ID = edId.getText().toString(),
        firstName = edFirstName.getText().toString(),
                lastName = edLastName.getText().toString(),
                factory = edFactory.getText().toString();
        if(ID.equals("")){
            id = 0;
        }
        else{
            id = Integer.parseInt(ID);
        }


        return new Employee(id,firstName,lastName,factory);
    }
}