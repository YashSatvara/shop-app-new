package com.example.shopapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Shop_detail extends Fragment {

    private String shopName;

    public Shop_detail() {
        // Required empty public constructor
    }

    public static Shop_detail newInstance(String shopName) {
        Shop_detail fragment = new Shop_detail();
        Bundle args = new Bundle();
        args.putString("shop_name", shopName); // Pass the shop name to the fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shopName = getArguments().getString("shop_name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_detail, container, false);

        // Display the shop name or category
        TextView shopNameTextView = view.findViewById(R.id.shop_name);
        shopNameTextView.setText(shopName); // Set the passed shop name

        return view;
    }
}
