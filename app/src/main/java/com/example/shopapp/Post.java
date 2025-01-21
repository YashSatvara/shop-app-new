package com.example.shopapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shopapp.Model.MenuModel;
import com.example.shopapp.Adapter.MenuAdapter;

import java.util.ArrayList;

public class Post extends Fragment {

    RecyclerView recyclerView;
    ArrayList<MenuModel> arrayList;
    MenuAdapter menuAdapter;
    private Button group_1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);
        recyclerView = view.findViewById(R.id.rcvview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();
        group_1 = view.findViewById(R.id.group_1);


        arrayList.add(new MenuModel(R.drawable.plus,"Create Post"));

        menuAdapter = new MenuAdapter(getContext(),arrayList);
        recyclerView.setAdapter(menuAdapter);

        return view;

    }
}
