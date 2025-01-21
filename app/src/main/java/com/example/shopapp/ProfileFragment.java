package com.example.shopapp;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.activity.OnBackPressedCallback;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.io.InputStream;

public class ProfileFragment extends Fragment {

    // View references
    private ImageView profilePicture;
    private EditText profileName, profileEmail, profilemobile, profile_birth, profile_gender;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
        args.putString("ARG_PARAM2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Handle system back press to navigate to HomeFragment
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navigateToFragment(new HomeFragment());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        profilePicture = view.findViewById(R.id.profile_picture);
        profileName = view.findViewById(R.id.profile_name);
        profileEmail = view.findViewById(R.id.profile_email);
        profilemobile = view.findViewById(R.id.profile_mobile);
        profile_birth = view.findViewById(R.id.profile_birth);
        profile_gender = view.findViewById(R.id.profile_gender);

        // Load profile data
        loadProfileData();

        // Setup navigation buttons
        setupNavigationButtons(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Reload profile data whenever the fragment becomes visible
        loadProfileData();
    }

    private void loadProfileData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "John Doe");
        String email = sharedPreferences.getString("email", "john.doe@example.com");
        String profilePictureUri = sharedPreferences.getString("profilePicture", null);
        String mobile = sharedPreferences.getString("mobile", "+91 9876543210");
        String birth = sharedPreferences.getString("address", "08-02-1999"); // Default address
        String gender = sharedPreferences.getString("Gender", "Male or Female");

        Log.d("ProfileEdit", "Loaded Phone: " + mobile);
        Log.d("ProfileEdit", "Loaded Address: " + birth);

        profileName.setText(name);
        profileEmail.setText(email);
        profilemobile.setText(mobile);
        profile_birth.setText(birth);
        profile_gender.setText(gender);


        if (profilePictureUri != null) {
            try {
                Uri uri = Uri.parse(profilePictureUri);
                InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                profilePicture.setImageBitmap(bitmap);
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                // Load a default profile picture in case of errors
                Glide.with(this)
                        .load("https://example.com/profile.jpg")
                        .placeholder(R.drawable.ring_4)
                        .into(profilePicture);
            }
        } else {
            Glide.with(this)
                    .load("https://example.com/profile.jpg")
                    .placeholder(R.drawable.ring_4)
                    .into(profilePicture);
        }
    }



    private void setupNavigationButtons(View view) {
        // Back button
        ImageView backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> navigateToFragment(new HomeFragment()));

        // Help layout
        LinearLayout helpLayout = view.findViewById(R.id.help);
        helpLayout.setOnClickListener(v -> navigateToFragment(new ChatFragment()));

        LinearLayout myorder = view.findViewById(R.id.myorder);
        myorder.setOnClickListener(v -> navigateToFragment(new OrderFragment()));

        // Edit profile
        ImageView editProfileButton = view.findViewById(R.id.notification_button);
        editProfileButton.setOnClickListener(v -> navigateToFragment(new profile_edit_Fragment()));

        // Wishlist layout
        LinearLayout wishlistLayout = view.findViewById(R.id.wish);
        wishlistLayout.setOnClickListener(v -> navigateToFragment(new Wishlist()));

        LinearLayout privacyLayout = view.findViewById(R.id.privacy);
        privacyLayout.setOnClickListener(v -> navigateToFragment(new privacy_policy()));

        // Notifications
        ImageView notificationButton = view.findViewById(R.id.notific);
        notificationButton.setOnClickListener(v -> navigateToFragment(new Notification()));
    }

    private void navigateToFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linearLayout, fragment)
                .addToBackStack(null)
                .commit();
    }
}
