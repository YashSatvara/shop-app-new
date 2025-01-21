package com.example.shopapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class First extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(First.this, Login.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finishAffinity();
        System.exit(0);
    }


}