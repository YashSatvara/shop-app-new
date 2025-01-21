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
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Categoryitemlist;
import com.example.shopapp.Model.CategoriesModel;
import com.example.shopapp.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private Context context;
    private List<CategoriesModel> categoryList;

    public CategoriesAdapter(Context context, List<CategoriesModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoriesModel category = categoryList.get(position);
        holder.categoryName.setText(category.getCategoryName());
        holder.categoryImage.setImageResource(category.getCategoryImage());

        // Handle click on category image
        holder.categoryImage.setOnClickListener(v -> {
            if (context instanceof AppCompatActivity) {
                AppCompatActivity activity = (AppCompatActivity) context;

                // Pass data to Categoryitemlist fragment
                Categoryitemlist fragment = new Categoryitemlist();
                Bundle args = new Bundle();
                args.putString("type", category.getCategoryName()); // Pass category name
                fragment.setArguments(args);

                // Replace the fragment
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.linearLayout, fragment) // Replace with correct container ID
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryName = itemView.findViewById(R.id.categoryName);
        }
    }
}
