package com.example.h071231017_finalproject;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeUtils {
    private static final String PREF_NAME = "theme_pref";
    private static final String KEY_THEME = "is_dark_theme";

    public static void setTheme(Context context) {
        boolean isDarkTheme = isDarkTheme(context);
        setDarkTheme(context, isDarkTheme);
    }

    public static void setDarkTheme(Context context, boolean isDark) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(KEY_THEME, isDark).apply();

        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static boolean isDarkTheme(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_THEME, false);
    }
}
