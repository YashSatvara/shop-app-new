package com.example.shopapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Model.itemsModel;
import com.example.shopapp.R;
import com.example.shopapp.WishlistManager; // Import WishlistManager

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    private List<itemsModel> wishlistItems;
    private Context context;

    // Constructor
    public WishlistAdapter(Context context, List<itemsModel> wishlistItems) {
        this.context = context;
        this.wishlistItems = wishlistItems;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wishlist, parent, false); // Use the correct item layout
        return new WishlistViewHolder(view);
    }


     @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        itemsModel item = wishlistItems.get(position);

        // Debug logs to check data
        Log.d("WishlistAdapter", "Binding item: " + (item != null ? item.getName() : "null item"));

        // Set dynamic data with fallback values
        holder.imageView.setImageResource(item.getImage() != 0 ? item.getImage() : R.drawable.img);
        holder.tvName.setText(item.getName() != null ? item.getName() : "Unnamed Product");
        holder.tvType.setText(item.getType() != null ? item.getType() : "Unknown Type");
        holder.tvPrice.setText("Rs " + (item.getPrice() != null ? item.getPrice() : 0.0));

        // Remove item from wishlist on click
         holder.removeItem.setOnClickListener(v -> {
             itemsModel removedItem = wishlistItems.get(position);

             WishlistManager.removeItemFromWishlist(context, removedItem);
             wishlistItems.remove(position);

             notifyItemRemoved(position);
             notifyItemRangeChanged(position, wishlistItems.size());

             Toast.makeText(context, removedItem.getName() + " removed from wishlist", Toast.LENGTH_SHORT).show();
         });

     }



    @Override
    public int getItemCount() {
        return wishlistItems.size();
    }

    public static class WishlistViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView, removeItem;
        public TextView tvName, tvType, tvPrice;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvType = itemView.findViewById(R.id.tv_item_desc);
            tvPrice = itemView.findViewById(R.id.tv_item_price);
            removeItem = itemView.findViewById(R.id.iv_deleteitem);
        }
    }
}
