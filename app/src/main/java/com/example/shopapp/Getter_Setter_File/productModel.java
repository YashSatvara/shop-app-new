package com.example.shopapp.Getter_Setter_File;

public class productModel {
    private  String name;
    private  String price;
    private  int image;

    public productModel(String name, String price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

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
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
