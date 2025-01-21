package com.example.shopapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shopapp.Adapter.PostAdapter;
import com.example.shopapp.Model.PostModel;

import java.util.ArrayList;
import java.util.List;


public class BlogFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        // Initialize RecyclerView
        LinearLayout layout=view.findViewById(R.id.create_post);
        RecyclerView recyclerView = view.findViewById(R.id.viewpost);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        layout.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Create New Post", Toast.LENGTH_SHORT).show();
            navigateTocreatepost();
        });
        // Populate post list
        List<PostModel> postList = new ArrayList<>();
        postList.add(new PostModel("Nisarg Soni", "5 minutes ago", R.drawable.img_7, "10 min",
                R.drawable.yash, "The Art of Learning", "A journey to knowledge",
                R.drawable.heart_2, R.drawable.eye, R.drawable.message));

        PostAdapter adapter = new PostAdapter(getContext(), postList);
        recyclerView.setAdapter(adapter);

        // Set onClickListener for create post button (ImageView or TextView)


        return view;
    }
    private void navigateTocreatepost() {
        Fragment create_post = new Create_post(); // Replace with your purchase fragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.linearLayout, create_post);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
