package com.example.shopapp.Model;

import java.util.Objects;

public class itemsModel {
    private int id;
    private String name;
    private String type;
    private String price;
    private int image; // This should be an int, as it's a drawable resource ID

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        itemsModel that = (itemsModel) o;
        return id == that.id; // Compare based on a unique identifier, e.g., 'id'
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Generate hash code based on the unique identifier
    }


    // Constructor
    public itemsModel(String name, String type, String price, int image) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.image = image;
    }

    public itemsModel(int productImageResId, String productNameText, String productDescriptionText, String productPriceText) {
        this.image = productImageResId;
        this.name = productNameText;
        this.type = productDescriptionText; // Assuming 'type' is used for the product description
        this.price = productPriceText;
    }


    // Getter methods for each field
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }



}

