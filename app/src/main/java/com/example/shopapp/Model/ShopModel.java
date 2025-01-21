package com.example.shopapp.Model;

public class ShopModel {
    private String shop_name;
    private int shop_img;

    public ShopModel(String shop_name, int shop_img) {
        this.shop_name = shop_name;
        this.shop_img = shop_img;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setName(String name) {
        this.shop_name = name;
    }

    public int getShop_img() {
        return shop_img;
    }

    public void setShop_img(int shop_img) {
        this.shop_img = shop_img;
    }
}
