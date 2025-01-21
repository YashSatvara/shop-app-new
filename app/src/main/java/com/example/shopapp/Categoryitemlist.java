package com.example.shopapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Adapter.CustomAdapter;
import com.example.shopapp.Model.itemsModel;

import java.util.ArrayList;
import java.util.List;

public class Categoryitemlist extends Fragment {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private List<itemsModel> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoryitemlist, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        // Get the category name passed from CategoriesAdapter
        String categoryName = getArguments() != null ? getArguments().getString("type") : "";

        // Fetch the products based on the selected category
        productList = fetchProductsByCategory(categoryName);

        // Set up the adapter with the product list
        adapter = new CustomAdapter(productList, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<itemsModel> fetchProductsByCategory(String categoryName) {
        List<itemsModel> products = new ArrayList<>();

        // Check the selected category and fetch respective products
        if ("Ring".equals(categoryName)) {
            products.add(new itemsModel("Ring", "Silver", "22000", R.drawable.ring_2));
            products.add(new itemsModel("Stone", "Ring", "20000", R.drawable.img3));
            products.add(new itemsModel("Lion", "Ring", "15000", R.drawable.ring_4));
            products.add(new itemsModel("Daimond", "Ring", "35000", R.drawable.ring_3));
            products.add(new itemsModel("Stambh", "Golden Ring", "12000", R.drawable.ring_5));
            products.add(new itemsModel("Leopard", "Ring", "18000", R.drawable.ring_6));
        }
         else if ("Pandal".equals(categoryName)) {
            products.add(new itemsModel("Ganpati", "Pandal", "15000", R.drawable.pandal_3));
            products.add(new itemsModel("Leopard", "Pandal", "25000", R.drawable.pandal_2));
            products.add(new itemsModel("Hanuman", "Pandal", "35000", R.drawable.pandal_1));
            products.add(new itemsModel("Om", "Pandal", "18000", R.drawable.pandal_4));
        }
        else if ("Earing".equals(categoryName)) {
            products.add(new itemsModel("Earing", "Golden", "12000", R.drawable.earings));
            products.add(new itemsModel("Gold", "Earing", "15000", R.drawable.earing));
            products.add(new itemsModel("Stone", "Earing", "22000", R.drawable.earing_2));
            products.add(new itemsModel("Moti", "Golden Earing", "13000", R.drawable.earing_3));
            products.add(new itemsModel("Golden", "Earing", "10500", R.drawable.earing_4));
        }
        else if ("Chain".equals(categoryName)) {
            products.add(new itemsModel("Rudraksh", "Gold Chain", "120000", R.drawable.chain_2));
            products.add(new itemsModel("Gold", "Chain", "150000", R.drawable.chain_3));
            products.add(new itemsModel("Daimond", "Chain", "220000", R.drawable.chain_4));
            products.add(new itemsModel("Golden", "Chain", "130000", R.drawable.chain_5));
            products.add(new itemsModel("Golden", "Chain", "105000", R.drawable.chain_6));
        }

        return products;  // Return the list of products for the selected category
    }
}
