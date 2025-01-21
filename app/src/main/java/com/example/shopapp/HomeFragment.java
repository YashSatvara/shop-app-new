package com.example.shopapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Adapter.CategoriesAdapter;
import com.example.shopapp.Adapter.CustomAdapter;
import com.example.shopapp.Adapter.ShopAdapter;
import com.example.shopapp.Adapter.SubscriptionAdapter;
import com.example.shopapp.Model.ShopModel;
import com.example.shopapp.Model.SubscriptionModel;
import com.example.shopapp.Model.CategoriesModel;
import com.example.shopapp.Model.itemsModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.om};
    String[] names = {"Rudraksh Gold", "Intricate Filigree Gold", "Daimond", "Hanuman Gold", "Om Gold"};
    String[] type = {"Mala", "Chain", "Gold Ring", "Pendant", "Ring"};
    String[] price = {" 2500", " 3500", " 5000", " 7000", " 6500"};

    private RecyclerView productRecyclerView, categories, shop;
    private RecyclerView subscriptionRecyclerView;
    private SubscriptionAdapter subscriptionAdapter;
    private CustomAdapter customAdapter;
    private List<itemsModel> itemsList = new ArrayList<>();
    private List<SubscriptionModel> subscriptionList;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        // Initialize Views
        searchView = view.findViewById(R.id.search_bar);
        ImageView searchIcon = view.findViewById(R.id.search_icon);
        ImageView micIcon = view.findViewById(R.id.mic_icon);
        searchView.setQueryHint("Search here...");
        searchView.setIconifiedByDefault(false); // Keep SearchView collapsed
       /* searchView.clearFocus();  // Prevents keyboard from opening
        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                searchView.clearFocus(); // Prevent keyboard from opening when returning to Home
            }

        });*/




        searchView.setOnClickListener(v -> {
            searchView.setIconified(false); // Expand search view
            searchView.requestFocus(); // Request focus to open the keyboard

            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT);
            }
        });


        micIcon.setOnClickListener(v -> {
            // Open voice recognition
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...");
            startActivityForResult(intent, 100);
        });


        // Populate product data
        for (int i = 0; i < names.length; i++) {
            itemsList.add(new itemsModel(names[i], type[i], price[i], images[i]));
        }


        // Initialize RecyclerViews
        setupRecyclerViews(view);

        return view;
    }

    private void setupRecyclerViews(View view) {
        // Product RecyclerView
        productRecyclerView = view.findViewById(R.id.productrecview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        productRecyclerView.setLayoutManager(layoutManager);
        productRecyclerView.setClipToPadding(false);

        int spacing = 10; // Spacing in pixels
        productRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.right = spacing;
            }
        });

// Log to check item count
        Log.d("HomeFragment", "Items list size: " + itemsList.size());

        customAdapter = new CustomAdapter(itemsList, getContext());
        productRecyclerView.setAdapter(customAdapter);


        // Subscription RecyclerView
        subscriptionRecyclerView = view.findViewById(R.id.precview);
        subscriptionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        subscriptionList = new ArrayList<>();
        subscriptionList.add(new SubscriptionModel("Basic", "Buy this basic subscription plan...", "₹699", "Valid for 1 month"));
        subscriptionList.add(new SubscriptionModel("Premium", "Get unlimited access to all...", "₹1299", "Valid for 3 months"));

        subscriptionAdapter = new SubscriptionAdapter(getContext(), subscriptionList);
        subscriptionRecyclerView.setAdapter(subscriptionAdapter);

        // Categories RecyclerView
        categories = view.findViewById(R.id.categories);
        categories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<CategoriesModel> categoryList = new ArrayList<>();
        categoryList.add(new CategoriesModel("Ring", R.drawable.ring_2));
        categoryList.add(new CategoriesModel("Pandal", R.drawable.pandal_2));
        categoryList.add(new CategoriesModel("Earring", R.drawable.earing));
        categoryList.add(new CategoriesModel("Chain", R.drawable.chain_1));

        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getContext(), categoryList);
        categories.setAdapter(categoriesAdapter);

        // Shop RecyclerView
        shop = view.findViewById(R.id.shop2);
        shop.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<ShopModel> shop2 = new ArrayList<>();
        shop2.add(new ShopModel("Cartler Jewellers", R.drawable.cartler));
        shop2.add(new ShopModel("Zaverat Jewellers", R.drawable.zaverat_logo));
        shop2.add(new ShopModel("Vinayak Jewellers", R.drawable.vinayak_logo));
        shop2.add(new ShopModel("Riddhi Jewellers", R.drawable.riddhi_logo));

        ShopAdapter shopAdapter = new ShopAdapter(getContext(), shop2);
        shop.setAdapter(shopAdapter);
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.linearLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Enable menu in the fragment
    }

}
