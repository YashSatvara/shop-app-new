package com.example.shopapp;

import com.example.shopapp.Model.itemsModel;
import java.util.ArrayList;
import java.util.List;

public class CartManager {


    private static List<itemsModel> cartItems = new ArrayList<>();

    public static void addToCart(itemsModel item) {
        cartItems.add(item);
    }

    public static void removeFromCart(String productId) {
        cartItems.removeIf(item -> item.getName().equals(productId));
    }

    public static List<itemsModel> getCartItems() {
        return new ArrayList<>(cartItems);
    }
}
