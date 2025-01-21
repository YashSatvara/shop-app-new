package com.example.shopapp.Getter_Setter_File;

public class itemsModel {

    private  String name;
    private  String type;
    private  String price;
    private  int image;

    public itemsModel(String name, String type, String price, int image) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
