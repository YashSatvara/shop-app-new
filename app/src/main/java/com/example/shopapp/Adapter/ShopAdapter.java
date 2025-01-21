package com.example.shopapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Shop_detail;
import com.example.shopapp.Model.ShopModel;
import com.example.shopapp.R;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private Context context;
    private List<ShopModel> shop2;

    public ShopAdapter(Context context, List<ShopModel> shop2) {
        this.context = context;
        this.shop2 = shop2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShopModel shop = shop2.get(position);

        // Set data to views
        holder.shop_name.setText(shop.getShop_name());
        holder.shop_img.setImageResource(shop.getShop_img());

        // Handle click on shop image
        holder.shop_img.setOnClickListener(v -> {
            if (context instanceof AppCompatActivity) {
                AppCompatActivity activity = (AppCompatActivity) context;

                // Create ShopDetail fragment and pass data via arguments
                Shop_detail fragment = new Shop_detail(); // Ensure this is your actual fragment
                Bundle args = new Bundle();
                args.putString("shop_name", shop.getShop_name()); // Pass the shop name
                args.putInt("shop_img", shop.getShop_img()); // Pass the shop image
                fragment.setArguments(args);

                // Perform fragment transaction
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.linearLayout, fragment) // Use the correct container ID
                        .addToBackStack(null) // Add transaction to back stack
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shop2.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView shop_img;
        TextView shop_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shop_img = itemView.findViewById(R.id.shop_img);
            shop_name = itemView.findViewById(R.id.shop_name);
        }
    }
}
