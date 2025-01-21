package com.example.shopapp;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Notification extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = getParentFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                } else {
                    // Navigate to HomeFragment
                    fragmentManager.beginTransaction()
                            .replace(R.id.linearLayout, new HomeFragment())
                            .commit();
                }
            }
        });

        ImageView back1 = view.findViewById(R.id.back1);

        // Set an OnClickListener on the LinearLayout
        back1.setOnClickListener(v -> {
            // Handle the click event for the LinearLayout
            // For example, navigate to a HelpFragment or perform another action
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.linearLayout, new ProfileFragment())
                    .addToBackStack(null) // Optional: Add to back stack if you want to navigate back
                    .commit();
        });
        return view;
    }

}