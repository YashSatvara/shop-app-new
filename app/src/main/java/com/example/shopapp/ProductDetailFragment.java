package com.example.shopapp;

import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopapp.Model.itemsModel;

public class ProductDetailFragment extends Fragment {

    private ImageView productImage;
    private TextView productName, productPrice, productDescription;
    private Button enquiryButton;
    private ImageButton backButton, favoriteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        // Initialize Views
        productImage = view.findViewById(R.id.productImage);
        productName = view.findViewById(R.id.productName);
        productDescription = view.findViewById(R.id.productDescription);
        enquiryButton = view.findViewById(R.id.enquiryButton);
        backButton = view.findViewById(R.id.backButton);
        favoriteButton = view.findViewById(R.id.favoriteButton);

        // Retrieve product details from SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ProductDetails", Context.MODE_PRIVATE);
        String productNameText = sharedPreferences.getString("name", "N/A");
        String productDescriptionText = sharedPreferences.getString("description", "N/A");
        String productPriceText = sharedPreferences.getString("price", "N/A");
        int productImageResId = sharedPreferences.getInt("productImage", R.drawable.img1);

        // Set product details to views
        productName.setText(productNameText);
        productDescription.setText(productDescriptionText);
        productImage.setImageResource(productImageResId);

        // Handle back button click
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        // Handle favorite button click
        favoriteButton.setOnClickListener(v -> {
            Toast.makeText(requireContext(), productNameText + " added to favorites", Toast.LENGTH_SHORT).show();
        });

        // Handle enquiry button click
        enquiryButton.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Enquiry Sent for " + productNameText, Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
