package com.example.myapplication;

import com.google.android.gms.maps.model.LatLng;

public class Place {
    private String name;
    private String phone;
    private String image;
    private int size;
    private int floor;
    private int price;
    private LatLng cords;
    private String text;
    private boolean invest;

    public Place(String name, String phone, LatLng cords,
                 int floor, int size, String image, int price, String text, boolean invest) {
        this.name = name;
        this.cords = cords;
        this.size = size;
        this.image = image;
        this.floor = floor;
        this.phone = phone;
        this.price = price;
        this.text = text;
        this.invest = invest;
    }


    public void setText(String text) {
        this.text = text;
    }


    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public void setCords(LatLng cords) {
        this.cords = cords;
    }

    public LatLng getCords() {
        return cords;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public boolean getInvest(){return invest;}

    public String toString() {
        return text;
    }
}
