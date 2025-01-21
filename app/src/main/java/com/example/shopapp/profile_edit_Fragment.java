package com.example.shopapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.InputStream;

public class profile_edit_Fragment extends Fragment {
    private ImageView imgProfilePicture;
    private EditText etName, etEmail, etPhone,etbirth, etgender;
    private Button btnSave, btnChangePicture;
    private static final int PICK_IMAGE_REQUEST = 1001;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_edit, container, false);

        imgProfilePicture = view.findViewById(R.id.imgProfilePicture);
        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etPhone = view.findViewById(R.id.etPhone);
        etbirth = view.findViewById(R.id.etbirth);
        etgender = view.findViewById(R.id.etgender);
        btnSave = view.findViewById(R.id.btnSave);
        btnChangePicture = view.findViewById(R.id.btnChangePicture);

        // Mock function to load profile details
        loadProfileDetails();

        // Save changes on button click
        btnSave.setOnClickListener(v -> saveProfileDetails());

        // Change profile picture
        btnChangePicture.setOnClickListener(v -> changeProfilePicture());

        return view;
    }

    private void loadProfileDetails() {
        // Load values from SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "John Doe");
        String email = sharedPreferences.getString("email", "john.doe@example.com");
        String phone = sharedPreferences.getString("phone", "+91 9876543210"); // Default mobile
        String birth = sharedPreferences.getString("address", "08-02-1999");
        String gender = sharedPreferences.getString("Gender", "Male or Female");
        String profilePicture = sharedPreferences.getString("profilePicture", null);

        // Debug logs to confirm loading
        Log.d("ProfileEdit", "Loaded Phone: " + phone);
        Log.d("ProfileEdit", "Loaded Address: " + birth);

        // Set loaded values to EditText fields
        etName.setText(name);
        etEmail.setText(email);
        etPhone.setText(phone);
        etbirth.setText(birth);
        etgender.setText(gender);

        // Load profile picture
        if (profilePicture != null) {
            Uri profilePictureUri = Uri.parse(profilePicture);
            setImageFromUri(profilePictureUri, imgProfilePicture);
        } else {
            Glide.with(this)
                    .load("https://example.com/profile.jpg")
                    .placeholder(R.drawable.ring_4)
                    .into(imgProfilePicture);
        }
    }


    private void saveProfileDetails() {
        String updatedName = etName.getText().toString().trim();
        String updatedEmail = etEmail.getText().toString().trim();
        String updatedPhone = etPhone.getText().toString().trim();
        String updatedbirth = etbirth.getText().toString().trim();
        String updatedgender = etgender.getText().toString().trim();

        if (updatedName.isEmpty() || updatedEmail.isEmpty() || updatedPhone.isEmpty() || updatedbirth.isEmpty() || updatedgender.isEmpty()) {
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", updatedName);
        editor.putString("email", updatedEmail);
        editor.putString("mobile", updatedPhone); // Change "phone" to "mobile"
        editor.putString("address", updatedbirth);
        editor.putString("address", updatedgender);
        editor.apply();

        Log.d("ProfileEdit", "Saved Mobile: " + updatedPhone);
        Log.d("ProfileEdit", "Saved Address: " + updatedbirth);

        Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();

        loadProfileDetails(); // Optional: Refresh the data in the edit fragment
    }




    private void changeProfilePicture() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true); // Optional: Restrict to local files
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            if (imageUri != null) {
                try {
                    requireContext().getContentResolver().takePersistableUriPermission(
                            imageUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    );

                    setImageFromUri(imageUri, imgProfilePicture);

                    // Save URI in SharedPreferences
                    requireContext().getSharedPreferences("UserProfile", AppCompatActivity.MODE_PRIVATE)
                            .edit()
                            .putString("profilePicture", imageUri.toString())
                            .apply();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(requireContext(), "Unable to load image.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void setImageFromUri(Uri uri, ImageView imageView) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
