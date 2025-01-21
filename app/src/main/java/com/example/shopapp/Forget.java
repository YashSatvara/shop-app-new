package com.example.shopapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Forget extends AppCompatActivity {
    Button btnreset, button1;
    EditText editemail;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget); // Set content view first

        // Initialize UI elements before using them
        editemail = findViewById(R.id.email);
        btnreset = findViewById(R.id.forgetpass_button);
        progressBar = findViewById(R.id.ProgressBar2);
        button1 = findViewById(R.id.forgetpass_button);
        mAuth = FirebaseAuth.getInstance();


        button1.setOnClickListener(v -> {
            Intent intent = new Intent(Forget.this, Login.class); // Ensure correct class name
            startActivity(intent);
            finish();
        });

        // Now you can interact with these elements
        progressBar.setVisibility(View.INVISIBLE); // Initially hide the progress bar
        btnreset.setVisibility(View.VISIBLE); // Show the reset button

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = editemail.getText().toString().trim();
                if (!TextUtils.isEmpty(strEmail)) {
                    ResetPassword(); // Call the method properly with parentheses
                    progressBar.setVisibility(View.VISIBLE); // Show progress bar while resetting
                    btnreset.setVisibility(View.INVISIBLE); // Hide the reset button
                } else {
                    editemail.setError("Email field can't be empty");
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void ResetPassword() {

        mAuth.sendPasswordResetEmail(strEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Forget.this, "Reset Password link has been sent to your registered Email", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Forget.this, Login.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Forget.this, "Error :- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                btnreset.setVisibility(View.VISIBLE);
            }
        });
    }
}
