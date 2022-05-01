package com.example.timekeepingmanagement.entity;

import java.io.Serializable;

public class Employee  implements Serializable {
    int id;
    String firstName, lastName, factory;

    public int getId() {
        return id;
    }

    public void setId(int idEmployee) {
        this.id = idEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Employee(int idEmployee, String firstName, String lastName, String factory) {
        this.id = idEmployee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.factory = factory;
    }

    public String toString(){
         String s= getId()+";"+getFirstName()+";"+getLastName()+";"+getFactory();
        return s.toLowerCase();

    }

    public Employee() {
    }
}
