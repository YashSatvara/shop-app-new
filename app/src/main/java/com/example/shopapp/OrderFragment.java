package com.example.shopapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopapp.Adapter.CartAdapter;
import com.example.shopapp.Model.itemsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class OrderFragment extends Fragment {
    private LinearLayout linearLayoutOrderSummary;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        linearLayoutOrderSummary = view.findViewById(R.id.ordersummary);

        // Set click listener for order summary
        linearLayoutOrderSummary.setOnClickListener(v -> {
            Fragment orderSummaryFragment = new order_summary();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.linearLayout, orderSummaryFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        // Handle back press properly
        requireActivity().getOnBackPressedDispatcher().addCallback(
                getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        handleBackNavigation();
                    }
                }
        );
    }

    private void handleBackNavigation() {
        FragmentManager fragmentManager = getParentFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.linearLayout, new ProfileFragment())
                    .commit();
        }
    }

    public static class CartFragment extends Fragment {

        private RecyclerView cartRecyclerView;
        private CartAdapter cartAdapter;
        private Button checkoutButton;

        // UI elements for totals
        private TextView totalProductPrice;
        private TextView shippingCharges;
        private TextView orderDiscount;
        private TextView finalPrice;

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_cart, container, false);

            // Initialize Views
            cartRecyclerView = view.findViewById(R.id.cartrecyclerview);
            checkoutButton = view.findViewById(R.id.checkoutButton);

            // Initialize TextViews for totals
            totalProductPrice = view.findViewById(R.id.totalProductPrice);
            shippingCharges = view.findViewById(R.id.shippingCharges);
            orderDiscount = view.findViewById(R.id.orderDiscount);
            finalPrice = view.findViewById(R.id.finalPrice);

            // Retrieve Cart Items
            List<itemsModel> cartItems = CartManager.getCartItems();

            // Set Up RecyclerView
            cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // Pass TextViews to the adapter
            cartAdapter = new CartAdapter(cartItems, getContext(),
                    totalProductPrice,
                    shippingCharges,
                    orderDiscount,
                    finalPrice);
            cartRecyclerView.setAdapter(cartAdapter);


            // Checkout Button Logic
            checkoutButton.setOnClickListener(v -> {
                // Save cart details to SharedPreferences
                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("CartDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Convert cart items to a JSON string
                JSONArray cartArray = new JSONArray();
                for (itemsModel item : cartItems) {
                    try {
                        JSONObject itemObject = new JSONObject();
                        itemObject.put("name", item.getName());
                        itemObject.put("type", item.getType());
                        itemObject.put("price", item.getPrice());
                        itemObject.put("image", item.getImage());
                        cartArray.put(itemObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                editor.putString("cartItems", cartArray.toString());

                // Save totals
                editor.putString("totalProductPrice", totalProductPrice.getText().toString());
                editor.putString("shippingCharges", shippingCharges.getText().toString());
                editor.putString("orderDiscount", orderDiscount.getText().toString());
                editor.putString("finalPrice", finalPrice.getText().toString());

                editor.apply();

                // Navigate to PurchaseFragment
                navigateToPurchaseFragment();
            });


            // Back Button Handling
            requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    handleBackNavigation();
                }
            });

            return view;
        }

        // Navigate to PurchaseFragment
        private void navigateToPurchaseFragment() {
            Fragment purchaseFragment = new PurchaseFragment(); // Replace with your purchase fragment
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.linearLayout, purchaseFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        // Handle Back Navigation
        private void handleBackNavigation() {
            FragmentManager fragmentManager = getParentFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
            } else {
                fragmentManager.beginTransaction()
                        .replace(R.id.linearLayout, new HomeFragment())
                        .commit();
            }
        }
    }
}
