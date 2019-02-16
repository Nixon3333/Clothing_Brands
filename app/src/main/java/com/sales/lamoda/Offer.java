package com.sales.lamoda;

import java.util.ArrayList;

public class Offer {

    public Offer(String name, String price, String url, ArrayList<String> pictures) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.pictures = pictures;
    }

    String name;
    String price;
    String url;
    ArrayList<String> pictures;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }
}
