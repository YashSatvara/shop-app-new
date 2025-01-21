package com.example.shopapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.shopapp.Model.itemsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WishlistManager {
    private static final String PREFS_NAME = "WishlistPrefs";
    private static final String KEY_WISHLIST = "wishlist";

    public static List<itemsModel> getWishlist(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_WISHLIST, null);
        if (json != null) {
            Type type = new TypeToken<List<itemsModel>>() {}.getType();
            return new Gson().fromJson(json, type);
        }
        return new ArrayList<>();
    }

    public static void addItemToWishlist(Context context, itemsModel item) {
        List<itemsModel> wishlist = getWishlist(context);
        wishlist.add(item);
        saveWishlist(context, wishlist);
    }

    public static void removeItemFromWishlist(Context context, itemsModel itemToRemove) {
        List<itemsModel> wishlist = getWishlist(context);

        if (wishlist.contains(itemToRemove)) {
            wishlist.remove(itemToRemove);
            Log.d("WishlistManager", "Item removed successfully: " + itemToRemove.getName());
        } else {
            Log.d("WishlistManager", "Item not found in wishlist: " + itemToRemove.getName());
        }

        saveWishlist(context, wishlist);
    }


    private static void saveWishlist(Context context, List<itemsModel> wishlist) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_WISHLIST, new Gson().toJson(wishlist));
        editor.apply();
    }
}

