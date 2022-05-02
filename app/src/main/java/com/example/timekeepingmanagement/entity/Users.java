package com.example.timekeepingmanagement.entity;

public class Users {
    int id, idEmployee;
    String username, passwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int inEmployee) {
        this.idEmployee = inEmployee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Users(int id, String username, String passwd, int idEmployee) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.username = username;
        this.passwd = passwd;
    }
}
