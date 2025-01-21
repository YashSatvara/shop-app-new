package com.example.shopapp;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopapp.Adapter.CustomAdapter;
import com.example.shopapp.Model.itemsModel;

import java.util.ArrayList;
import java.util.List;

public class AllProductFragment extends Fragment {

    int images[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.neckless_2, R.drawable.ring_2, R.drawable.ganpati, R.drawable.earings};
    String names[] = {"Timeless Elegance", "Intricate Filigree", "Timeless Elegance", "Geometric Gold Bracelet", "Gold", "Daimond", "Ganpati", "Gold"};
    String type[] = {"Gold", "Chain", "Necklace", "Pendant", "Necklace", "Ring", "Pandel", "Earings"};
    String price[] = {" 2500", " 3500", " 5000", " 7000", " 9000", " 1200", " 9500", " 6500"};

    List<itemsModel> itemsList = new ArrayList<>();
    List<itemsModel> filteredList = new ArrayList<>();

    RecyclerView recyclerView ,recyclerView1, recyclerView2;
    HomeFragment categoriesFragment,shop;
    CustomAdapter customAdapter;
    SearchView searchView;
    TextView noPostsText,noPostsSubtext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_product, container, false);

        if (images.length != names.length || names.length != type.length || type.length != price.length) {
            throw new IllegalStateException("Array lengths do not match!");
        }

        recyclerView = v.findViewById(R.id.gridviewall);
        searchView = v.findViewById(R.id.search_barall);

        searchView.setQueryHint("Search here....");
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);

        TextView searchText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        if (searchText != null) {
            int hintColor = getResources().getColor(android.R.color.white);
            searchText.setHintTextColor(hintColor);
        }

        searchView.clearFocus();


        // Set up the search bar listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // Don't need to handle this
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText); // Filter the list as text changes
                return true;
            }
        });

        // Initialize data and fill the itemsList
        for (int i = 0; i < Math.min(names.length, images.length); i++) {
            itemsModel item = new itemsModel(names[i], type[i], price[i], images[i]);
            itemsList.add(item);
        }



        // Initially, display the full itemsList
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        customAdapter = new CustomAdapter(itemsList, getContext());
        recyclerView.setAdapter(customAdapter);




        return v;
    }

    // Filter the list based on user input
    private void filterList(String text) {
        filteredList.clear(); // Clear previous filtered results

        for (itemsModel item : itemsList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase()) ||
                    item.getType().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item); // Add matching items
            }
        }

        // Update adapter with the filtered list
        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        } else {
            customAdapter.setFilteredList(filteredList);
        }
    }


    // RecyclerView Adapter for displaying items in grid

}
