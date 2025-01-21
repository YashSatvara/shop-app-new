package com.example.shopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.MainActivity;
import com.example.shopapp.Model.SubscriptionModel;
import com.example.shopapp.R;
import com.example.shopapp.ViewSubscriptionFragment;

import java.util.List;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder> {

    private Context context;
    private List<SubscriptionModel> subscriptionList;
    private static final String PREFS_NAME = "SubscriptionPrefs";

    public SubscriptionAdapter(Context context, List<SubscriptionModel> subscriptionList) {
        this.context = context;
        this.subscriptionList = subscriptionList;
    }

    @NonNull
    @Override
    public SubscriptionAdapter.SubscriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.priceplans, parent, false);
        return new SubscriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionAdapter.SubscriptionViewHolder holder, int position) {

        SubscriptionModel model = subscriptionList.get(position);
        holder.titleTextView.setText(model.getTitle());
        holder.descriptionTextView.setText(model.getDescription());
        holder.priceTextView.setText(model.getPrice());
        holder.validityTextView.setText(model.getValidity());

        // Set click listener for selectButton
        holder.selectButton.setOnClickListener(view -> {
            // Store selected subscription details in SharedPreferences
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("title", model.getTitle());
            editor.putString("description", model.getDescription());
            editor.putString("price", model.getPrice());
            editor.putString("validity", model.getValidity());
            editor.apply();

            // Navigate to ViewSubscriptionFragment
            ViewSubscriptionFragment viewSubscriptionFragment = new ViewSubscriptionFragment();
            ((MainActivity) context).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.linearLayout, viewSubscriptionFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return subscriptionList.size();
    }

    public static class SubscriptionViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView, priceTextView, validityTextView;
        Button selectButton;
        CardView cardView;

        public SubscriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.pricplanecardView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            validityTextView = itemView.findViewById(R.id.validityTextView);
            selectButton = itemView.findViewById(R.id.selectButton);
        }
    }
}
