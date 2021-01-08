package com.example.jit.lezzaproject;

public class Order {

    private String name;
    private int price;
    private int amount;
    private String date;
    private String address;
    private String phoneNumber;
    private String resturantName;
    private String email;


    public Order(String name, int price, int amount, String date) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.date = date;


    }

    public Order(String name, int price, int amount, String date, String address) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.date = date;
        this.address = address;

    }

    public Order(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResturantName() {
        return resturantName;
    }

    public void setResturantName(String resturantName) {
        this.resturantName = resturantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
