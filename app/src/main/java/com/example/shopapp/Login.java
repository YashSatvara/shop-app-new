package com.example.shopapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class Login extends AppCompatActivity {

    EditText editTextemail, editTextpassword;
    TextView textView1, textView2;
    FirebaseAuth mAuth;
    GoogleSignInClient googleSignInClient;
    ProgressBar progressBar;
    Button buttonlogin;
    CheckBox rememberMe;

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                        try {
                            GoogleSignInAccount signInAccount = accountTask.getResult(ApiException.class);
                            String idToken = signInAccount.getIdToken();

                            // Show confirmation dialog to the user
                            showLoginConfirmationDialog(idToken);

                        } catch (ApiException e) {
                            Log.e("Google Sign-In", "Google sign-in failed", e);
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);

        // Initialize views
        editTextemail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.password);
        rememberMe = findViewById(R.id.rememberMe);
        progressBar = findViewById(R.id.progressBar1);
        buttonlogin = findViewById(R.id.login_btn);
        Button signInButton = findViewById(R.id.SignIn);
        textView1 = findViewById(R.id.your_text_view_id);
        textView2 = findViewById(R.id.forgotPassword1);

        textView1.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Signup.class);
            startActivity(intent);
            finish();
        });

        textView2.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Forget.class);
            startActivity(intent);
            finish();
        });

        // Google Sign-In options
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, options);

        // Google Sign-In button click listener
        signInButton.setOnClickListener(v -> {
            Intent signInIntent = googleSignInClient.getSignInIntent();
            activityResultLauncher.launch(signInIntent);
        });

        // Email and password login handling
        buttonlogin.setOnClickListener(v -> {
            String email = editTextemail.getText().toString();
            String password = editTextpassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                            navigateToHomepage();
                        } else {
                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // Remember Me handling
        handleRememberMe();
    }

    private void handleRememberMe() {
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        if ("true".equals(checkbox)) {
            navigateToHomepage();
        }

        rememberMe.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            SharedPreferences.Editor editor = getSharedPreferences("checkbox", MODE_PRIVATE).edit();
            editor.putString("remember", isChecked ? "true" : "false");
            editor.apply();
        });
    }

    private void showLoginConfirmationDialog(String idToken) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Login")
                .setMessage("Do you want to log in with this Google account?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Proceed with Firebase authentication only if the user confirms
                    firebaseAuthWithGoogle(idToken);
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                    // Sign out from Google to prevent re-authentication on next launch
                    googleSignInClient.signOut()
                            .addOnCompleteListener(task -> Toast.makeText(Login.this, "Canceled Google sign-in", Toast.LENGTH_SHORT).show());
                })
                .show();
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Signed in successfully!", Toast.LENGTH_SHORT).show();
                        navigateToHomepage();
                    } else {
                        Log.w("Google Sign-In", "signInWithCredential:failure", task.getException());
                        Toast.makeText(Login.this, "Failed to sign in: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void navigateToHomepage() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
