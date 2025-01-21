package com.example.shopapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shopapp.Model.ResultSavelogin;
import com.example.shopapp.Model.Savelogin;
import com.example.shopapp.Webservice.Appclient;
import com.example.shopapp.Webservice.RetrofitAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    private EditText editTextemail, editTextpassword, editTextconfirmpassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Button buttonReg;
    private TextView text1;
    ArrayList<Savelogin> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        editTextemail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.password);
        editTextconfirmpassword = findViewById(R.id.confirm_button);
        progressBar = findViewById(R.id.progressBar);
        buttonReg = findViewById(R.id.button_reg);
        text1 = findViewById(R.id.login_text_view1);

        // Register button click listener
        buttonReg.setOnClickListener(v -> registerUser());

        // Login text click listener (redirect to login activity)
        text1.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
            finish();
        });

        // Handle window insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void registerUser() {
        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String confirmPassword = editTextconfirmpassword.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(email)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Signup.this, "Enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Signup.this, "Enter a valid password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE); // Hide progress bar after task completion

                    if (task.isSuccessful()) {
                        Toast.makeText(Signup.this, "Account Created. Please log in.", Toast.LENGTH_SHORT).show();

                        // Sync data with backend via Retrofit
                        syncUserData(email, password);

                        // Redirect to login page
                        Intent intent = new Intent(Signup.this, Login.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Registration failed.";
                        Toast.makeText(Signup.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void syncUserData(String email, String password) {
        RetrofitAPI apiInterface = Appclient.getclient().create(RetrofitAPI.class);
        Call<ResultSavelogin> call = apiInterface.insert(email, password);

        call.enqueue(new Callback<ResultSavelogin>() {
            @Override
            public void onResponse(Call<ResultSavelogin> call, Response<ResultSavelogin> response) {

                arrayList = (ArrayList<Savelogin>) response.body().getSavelogin();
                SharedPreferences pref = getSharedPreferences("mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = pref.edit();
                ed.putString("uid", arrayList.get(0).getUid());
                ed.putString("email", email);
                ed.putString("password", password);
                ed.apply();


            }

            @Override
            public void onFailure(Call<ResultSavelogin> call, Throwable t) {
                Toast.makeText(Signup.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Signup.this, Login.class);
        startActivity(intent);
        finish();
    }
}
