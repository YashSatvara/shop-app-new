package com.example.shopapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LoyaltyFragment extends Fragment {

    TextView viewallpoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loyalty, container, false);
        viewallpoint = view.findViewById(R.id.view_all_point);
        viewallpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AllProductFragment
                Fragment loyaltyProgramFragment = new LoyaltyProgramFragment(); // Replace with your AllProductFragment class
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.linearLayout, loyaltyProgramFragment); // Replace R.id.fragment_container with the actual container ID
                transaction.addToBackStack(null); // Adds to the back stack for backward navigation
                transaction.commit();
            }
        });


        return  view;
    }
}