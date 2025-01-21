package com.example.shopapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.MainActivity;
import com.example.shopapp.Model.productModel;
import com.example.shopapp.ProductDetailFragment;
import com.example.shopapp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final Context context1; // Context variable
    private final List<productModel> productList;
    private static final String PREFS_NAME = "ProductDetails";

    // Constructor updated to accept a Context parameter
    public ProductAdapter(Context context, List<productModel> productList) {
        this.context1 = context; // Initialize context1
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        productModel product = productList.get(position);
        holder.imageView.setImageResource(product.getImage());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());

        holder.productLayout.setOnClickListener(view -> {
            // Store selected product details in SharedPreferences
            SharedPreferences sharedPreferences = context1.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("productName", product.getName());
            editor.putString("productPrice", product.getPrice());
            editor.putInt("productImage", product.getImage());
            editor.apply();

            // Navigate to ProductDetailFragment
            ProductDetailFragment productDetailFragment = new ProductDetailFragment();
            ((MainActivity) context1).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.linearLayout, productDetailFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        public ImageView imageView;
        LinearLayout productLayout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pimage);
            productName = itemView.findViewById(R.id.pname);
            productPrice = itemView.findViewById(R.id.pprice);
            productLayout = itemView.findViewById(R.id.productdetaillayout);
        }
    }
}
