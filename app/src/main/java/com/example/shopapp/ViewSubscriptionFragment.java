package com.example.shopapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ViewSubscriptionFragment extends Fragment {

    private static final String PREFS_NAME = "SubscriptionPrefs";
    TextView discount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_subscription, container, false);

        TextView discount = view.findViewById(R.id.discount);
        String text = "10% DISCOUNT MORE";

// Create a SpannableString
        SpannableString spannable = new SpannableString(text);

// Apply styles to "10%"
        spannable.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.Skin)),
                0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        spannable.setSpan(
                new RelativeSizeSpan(1.5f), // Make "10%" larger
                0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

// Apply styles to "DISCOUNT MORE"
        spannable.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.black)),
                0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        spannable.setSpan(
                new StyleSpan(Typeface.BOLD), // Make "DISCOUNT MORE" bold
                4, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

// Set the styled text to the TextView
        discount.setText(spannable);


        return view;
    }


}
