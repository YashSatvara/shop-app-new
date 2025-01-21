package com.example.shopapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Adapter.CustomAdapter;
import com.example.shopapp.Model.itemsModel;

import java.util.ArrayList;
import java.util.List;

public class Shop2 extends Fragment {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private List<itemsModel> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop2, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        // Use GridLayoutManager for displaying items in a grid
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Get the category name passed from the ShopAdapter
        String shopName = getArguments() != null ? getArguments().getString("type") : "";

        // Fetch the products based on the selected category
        productList = fetchProductsByCategory(shopName);

        // Set up the adapter with the product list
        adapter = new CustomAdapter(productList, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<itemsModel> fetchProductsByCategory(String shopName) {
        List<itemsModel> products = new ArrayList<>();

        // Check the selected category and fetch respective products
        if ("Ring".equals(shopName)) {
            products.add(new itemsModel("Suvarna Nagari Jewels", "", "", R.drawable.suvarna_logo));
            products.add(new itemsModel("Zaverat Jewellers", "", "", R.drawable.zaverat_logo));
            products.add(new itemsModel("Riddhi Jewellers", "", "", R.drawable.riddhi_logo));
            products.add(new itemsModel("Vinayak Jewellers", "", "", R.drawable.vinayak_logo));
        } else if ("Earing".equals(shopName)) {
            products.add(new itemsModel("Earing", "Golden", "150", R.drawable.earing));
            products.add(new itemsModel("Gold Ring", "Gold", "250", R.drawable.earing));
        } else {
            // Add some default items if no category is selected
            products.add(new itemsModel("Default Item 1", "Category 1", "100", R.drawable.img1));
            products.add(new itemsModel("Default Item 2", "Category 1", "200", R.drawable.img2));
        }

        return products;
    }
}
