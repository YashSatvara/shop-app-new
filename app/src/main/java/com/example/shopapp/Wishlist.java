package com.example.shopapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopapp.Adapter.WishlistAdapter;
import com.example.shopapp.Model.itemsModel;

import java.util.List;

public class Wishlist extends Fragment {

    private WishlistAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        // Fetch wishlist items from WishlistManager
        List<itemsModel> wishlistItems = WishlistManager.getWishlist(requireContext());

        // Log to ensure the data is being fetched correctly
        Log.d("WishlistFragment", "Fetched wishlist items: " + wishlistItems);

        // Initialize RecyclerView and Adapter
        RecyclerView recyclerView = view.findViewById(R.id.wishlistrecview);
        WishlistAdapter adapter = new WishlistAdapter(getContext(), wishlistItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();

        // Fetch updated wishlist items
        List<itemsModel> updatedWishlistItems = WishlistManager.getWishlist(requireContext());

        // Log to check updated data
        Log.d("WishlistFragment", "Updated wishlist items: " + updatedWishlistItems);

        // Initialize RecyclerView and set the adapter if the list is not empty
        RecyclerView recyclerView = getView().findViewById(R.id.wishlistrecview);
        if (recyclerView != null) {
            if (updatedWishlistItems != null && !updatedWishlistItems.isEmpty()) {
                WishlistAdapter adapter = new WishlistAdapter(getContext(), updatedWishlistItems);
                recyclerView.setAdapter(adapter);
            } else {
                Log.d("WishlistFragment", "Wishlist is empty or null.");
            }
        }
    }

}


