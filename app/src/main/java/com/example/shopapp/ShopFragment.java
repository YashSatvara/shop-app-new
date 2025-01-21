package com.example.shopapp;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopapp.Adapter.CustomAdapter;
import com.example.shopapp.Adapter.BlogPostAdapter;
import com.example.shopapp.Model.itemsModel;
import com.example.shopapp.Model.BlogPostModel;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    // Dummy data arrays
    int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.neckless_2, R.drawable.ring_2, R.drawable.ganpati, R.drawable.earings};
    String[] names = {"Timeless Elegance Gold", "Intricate Filigree 24k", "Timeless Elegance Gold", "Geometric Gold Bracelet", "Neckless Gold", "Diamond Ring", "Ganpati Pendant", "Gold Earrings"};
    String[] types = {"Ring", "Chain", "Necklace", "Pendant", "Necklace", "Pendant", "Ganpati Pendant", "Golden Earrings"};
    String[] prices = {"2500", "3500", "5000", "7000", "9000", "12000", "9500", "6500"};

    List<itemsModel> itemsList = new ArrayList<>();
    List<itemsModel> filteredList = new ArrayList<>();
    List<BlogPostModel> blogPostList = new ArrayList<>();

    RecyclerView recyclerView, blogRecyclerView;
    CustomAdapter customAdapter;
    BlogPostAdapter blogPostAdapter;
    SearchView searchView;
    TextView noPostsText, noPostsSubtext;
    Button seeAllProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        // Initialize views
        recyclerView = view.findViewById(R.id.gridview);
        searchView = view.findViewById(R.id.search_bar);
        seeAllProducts = view.findViewById(R.id.seeallprod);
        blogRecyclerView = view.findViewById(R.id.recview);
        noPostsText = view.findViewById(R.id.no_posts_text);
        noPostsSubtext = view.findViewById(R.id.no_posts_subtext);

        setupSearchView();
        setupSeeAllProducts();

        populateItemsList();
        setupRecyclerView();

        initializeBlogPosts();
        setupBlogRecyclerView();

        return view;
    }

    private void setupSearchView() {
        searchView.setQueryHint("Search here...");
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void setupSeeAllProducts() {
        seeAllProducts.setOnClickListener(v -> {
            Fragment allProductFragment = new AllProductFragment(); // Replace with your actual fragment class
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.linearLayout, allProductFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }



    private void populateItemsList() {
        for (int i = 0; i < names.length; i++) {
            itemsModel item = new itemsModel(names[i], types[i], prices[i], images[i]);
            itemsList.add(item);
        }
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        customAdapter = new CustomAdapter(itemsList, getContext());
        recyclerView.setAdapter(customAdapter);
    }

    private void filterList(String query) {
        filteredList.clear();
        for (itemsModel item : itemsList) {
            if (item.getName().toLowerCase().contains(query.toLowerCase()) ||
                    item.getType().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        }
        customAdapter.setFilteredList(filteredList);
    }

    private void initializeBlogPosts() {
        // Add blog post data or leave empty to show "No posts" message
        blogPostList.clear();
    }

    private void setupBlogRecyclerView() {
        if (blogPostList.isEmpty()) {
            blogRecyclerView.setVisibility(View.GONE);
            noPostsText.setVisibility(View.VISIBLE);
            noPostsSubtext.setVisibility(View.VISIBLE);
        } else {
            blogRecyclerView.setVisibility(View.VISIBLE);
            noPostsText.setVisibility(View.GONE);
            noPostsSubtext.setVisibility(View.GONE);
            blogRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            blogPostAdapter = new BlogPostAdapter(blogPostList, getContext());
            blogRecyclerView.setAdapter(blogPostAdapter);
        }
    }
}
