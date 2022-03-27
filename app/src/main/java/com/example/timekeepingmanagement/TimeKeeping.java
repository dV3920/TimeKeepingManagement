package com.example.timekeepingmanagement;

import java.util.Date;

public class TimeKeeping {
    int id, idEmployee;
    Date dateTimeKeeping;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Date getDateTimeKeeping() {
        return dateTimeKeeping;
    }

    public void setDateTimeKeeping(Date dateTimeKeeping) {
        this.dateTimeKeeping = dateTimeKeeping;
    }

    public TimeKeeping(int id, int idEmployee, Date dateTimeKeeping) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.dateTimeKeeping = dateTimeKeeping;
    }

    public TimeKeeping() {
    }
}
