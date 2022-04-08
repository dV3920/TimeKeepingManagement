package com.example.timekeepingmanagement.entity;

public class InfoTimeKeeping {
    int idTime, idProduct, num1Pro,num0Pro;

    public InfoTimeKeeping() {
    }

    public int getIdTime() {
        return idTime;
    }

    public void setIdTime(int idTime) {
        this.idTime = idTime;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getNum1Pro() {
        return num1Pro;
    }

    public void setNum1Pro(int num1Pro) {
        this.num1Pro = num1Pro;
    }

    public int getNum0Pro() {
        return num0Pro;
    }

    public void setNum0Pro(int num0Pro) {
        this.num0Pro = num0Pro;
    }

    public InfoTimeKeeping(int idTime, int idProduct, int num1Pro, int num0Pro) {
        this.idTime = idTime;
        this.idProduct = idProduct;
        this.num1Pro = num1Pro;
        this.num0Pro = num0Pro;
    }
}
