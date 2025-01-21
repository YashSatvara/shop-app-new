package com.example.shopapp.Getter_Setter_File;

public class SubscriptionModel {

    private String title;
    private String description;
    private String price;
    private String validity;

    public SubscriptionModel(String title, String description, String price, String validity) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.validity = validity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }
}
