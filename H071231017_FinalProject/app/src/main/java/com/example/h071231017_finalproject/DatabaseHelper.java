package com.example.h071231017_finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "restaurant_db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_FAVORITES = "favorites";
    private static final String KEY_ID = "id";
    private static final String KEY_RESTAURANT_ID = "restaurant_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PICTURE_ID = "picture_id";
    private static final String KEY_CITY = "city";
    private static final String KEY_RATING = "rating";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_RESTAURANT_ID + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_PICTURE_ID + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_RATING + " REAL"
                + ", food_menu TEXT"
                + ", drink_menu TEXT"
                + ")";
        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    public void addFavorite(Favorite favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RESTAURANT_ID, favorite.getRestaurantId());
        values.put(KEY_NAME, favorite.getName());
        values.put(KEY_DESCRIPTION, favorite.getDescription());
        values.put(KEY_PICTURE_ID, favorite.getPictureId());
        values.put(KEY_CITY, favorite.getCity());
        values.put(KEY_RATING, favorite.getRating());
        values.put("food_menu", favorite.getFoodMenu());
        values.put("drink_menu", favorite.getDrinkMenu());
        db.insert(TABLE_FAVORITES, null, values);
        db.close();
    }

    public Favorite getFavoriteById(String restaurantId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITES, null,
                KEY_RESTAURANT_ID + "=?",
                new String[]{restaurantId}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Favorite favorite = new Favorite();
            favorite.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)));
            favorite.setRestaurantId(cursor.getString(cursor.getColumnIndexOrThrow(KEY_RESTAURANT_ID)));
            favorite.setName(cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
            favorite.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION)));
            favorite.setPictureId(cursor.getString(cursor.getColumnIndexOrThrow(KEY_PICTURE_ID)));
            favorite.setCity(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CITY)));
            favorite.setRating(cursor.getDouble(cursor.getColumnIndexOrThrow(KEY_RATING)));
            favorite.setFoodMenu(cursor.getString(cursor.getColumnIndexOrThrow("food_menu")));
            favorite.setDrinkMenu(cursor.getString(cursor.getColumnIndexOrThrow("drink_menu")));
            cursor.close();
            return favorite;
        }

        return null;
    }

    public void deleteFavorite(String restaurantId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, KEY_RESTAURANT_ID + " = ?", new String[]{restaurantId});
        db.close();
    }

    public boolean isFavorite(String restaurantId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITES,
                new String[]{KEY_ID},
                KEY_RESTAURANT_ID + "=?",
                new String[]{restaurantId},
                null, null, null, null);

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public List<Favorite> getAllFavorites() {
        List<Favorite> favoriteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITES, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Favorite favorite = new Favorite();
                favorite.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)));
                favorite.setRestaurantId(cursor.getString(cursor.getColumnIndexOrThrow(KEY_RESTAURANT_ID)));
                favorite.setName(cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
                favorite.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION)));
                favorite.setPictureId(cursor.getString(cursor.getColumnIndexOrThrow(KEY_PICTURE_ID)));
                favorite.setCity(cursor.getString(cursor.getColumnIndexOrThrow(KEY_CITY)));
                favorite.setRating(cursor.getDouble(cursor.getColumnIndexOrThrow(KEY_RATING)));
                favorite.setFoodMenu(cursor.getString(cursor.getColumnIndexOrThrow("food_menu")));
                favorite.setDrinkMenu(cursor.getString(cursor.getColumnIndexOrThrow("drink_menu")));
                favoriteList.add(favorite);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return favoriteList;
    }

    public void clearAllFavorites() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, null, null);
        db.close();
    }
}
