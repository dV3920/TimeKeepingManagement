package com.example.timekeepingmanagement.entity;

import java.io.Serializable;
// Nhi test
public class Product implements Serializable {
    int id;
    String name;
    float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public String toString(){
        return this.id +  this.getName() + this.getPrice();
    }
}
