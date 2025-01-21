package com.example.shopapp;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopapp.Adapter.SubscriptionAdapter;
import com.example.shopapp.Model.SubscriptionModel;

import java.util.ArrayList;
import java.util.List;

public class PriceFragment extends Fragment {

    private RecyclerView recyclerView;
    private SubscriptionAdapter adapter;
    private List<SubscriptionModel> subscriptionList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_price, container, false);
        recyclerView = view.findViewById(R.id.pricelistrecview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        subscriptionList = new ArrayList<>();
        subscriptionList.add(new SubscriptionModel("Basic", "Buy this pro subscription pack and get discount up to 10% on any products you bought from us!", "₹699", "Valid for 1 month"));
        subscriptionList.add(new SubscriptionModel("Pro", "Buy this pro subscription plan and get discounts  up to\n10% on any pproducts you bought from us!\n", "₹999", "Valid for 6 months"));

        adapter = new SubscriptionAdapter(getContext(), subscriptionList);
        recyclerView.setAdapter(adapter);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                handleBackNavigation();
            }
        });

        return view;
    }
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