package com.example.shopapp.Model;

public class CategoriesModel {
    private String categoryName;
    private int categoryImage;

    public CategoriesModel(String categoryName, int categoryImage) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setName(String name) {
        this.categoryName = name;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }
}
