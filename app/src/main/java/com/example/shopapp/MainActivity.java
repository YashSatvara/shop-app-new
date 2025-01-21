package com.example.shopapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private LinearLayout linearLayout;  // Change to LinearLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottomView);
        linearLayout = findViewById(R.id.linearLayout);

        // Set OnClickListener for the LinearLayout (click_home)
        LinearLayout clickHomeLayout = findViewById(R.id.click_home);
        clickHomeLayout.setOnClickListener(v -> {
            // Navigate to HomeFragment when the LinearLayout is clicked
            loadFragment(new HomeFragment(), false);
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                loadFragment(new HomeFragment(), false);
            } else if (itemId == R.id.nav_shop) {
                loadFragment(new ShopFragment(), false);
            } else if (itemId == R.id.store) {
                loadFragment(new store(), false);
            } else if (itemId == R.id.cart) {
                loadFragment(new OrderFragment.CartFragment(), false);
            }

            return true;
        });

        loadFragment(new HomeFragment(), true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_nav_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile_page) {
            loadFragment(new ProfileFragment(), false); // Load the CartFragment
            return true;
        } else if (id == R.id.membership) {
            loadFragment(new PriceFragment(), false);
            return true;
        }  else if (id == R.id.click_home) {
            loadFragment(new HomeFragment(), false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.linearLayout, fragment); // Replace with the container ID

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            // If there's something in the back stack, pop the last fragment
            fragmentManager.popBackStack();
        } else {
            // If no fragments are left, call super to handle default back action
            super.onBackPressed();
        }
    }

}
