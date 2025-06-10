package com.example.h071231017_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private boolean isNavigated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button btnExplore = findViewById(R.id.btn_explore);
        btnExplore.setOnClickListener(v -> {
            if (!isNavigated) {
                isNavigated = true;
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });

        new Handler().postDelayed(() -> {
            if (!isNavigated) {
                isNavigated = true;
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}
