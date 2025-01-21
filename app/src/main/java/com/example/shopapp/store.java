package com.example.shopapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import com.example.shopapp.Adapter.ShopAdapter;
import com.example.shopapp.Model.ShopModel;

public class store extends Fragment {

    private RecyclerView recyclerView;
    private ShopAdapter shopAdapter;
    private List<ShopModel> shopList;

    public store() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        recyclerView = view.findViewById(R.id.storeGrid);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Grid layout with 2 columns

        shopList = new ArrayList<>();
        loadShopData();

        shopAdapter = new ShopAdapter(getContext(), shopList);
        recyclerView.setAdapter(shopAdapter);

        return view;
    }

    private void loadShopData() {
        // Dummy shop data (Replace with real data if needed)
        shopList.add(new ShopModel("Suvarna Nagari Jewels", R.drawable.suvarna_logo));
        shopList.add(new ShopModel("Zaverat Jewellers", R.drawable.zaverat_logo));
        shopList.add(new ShopModel("Riddhi Jewellers", R.drawable.riddhi_logo));
        shopList.add(new ShopModel("Vinayak Jewellers", R.drawable.vinayak_logo));

    }
}
