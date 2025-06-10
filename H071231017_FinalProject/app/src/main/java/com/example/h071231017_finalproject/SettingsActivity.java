package com.example.h071231017_finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private Switch switchTheme;
    private Button btnResetData, btnAbout;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchTheme = findViewById(R.id.switch_theme);
        btnResetData = findViewById(R.id.btn_reset_data);
        btnAbout = findViewById(R.id.btn_about);

        dbHelper = new DatabaseHelper(this);

        switchTheme.setChecked(ThemeUtils.isDarkTheme(this));

        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ThemeUtils.setDarkTheme(SettingsActivity.this, isChecked);
                recreate();
            }
        });

        btnResetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResetConfirmationDialog();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAboutDialog();
            }
        });
    }

    private void showResetConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Reset Data")
                .setMessage("Are you sure you want to reset all favorite data?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.clearAllFavorites(); // Reset data favorit
                        Toast.makeText(SettingsActivity.this, "All favorite data has been reset", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("FoodieNest\nVersion 1.0\n\nAn application to find and save your favorite restaurants")
                .setPositiveButton("OK", null)
                .show();
    }
}
