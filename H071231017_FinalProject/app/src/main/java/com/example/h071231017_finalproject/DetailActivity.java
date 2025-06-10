package com.example.h071231017_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgRestaurant;
    private TextView tvName, tvAddress, tvCity, tvDescription, tvFoodMenu, tvDrinkMenu;
    private RatingBar ratingBar;
    private FloatingActionButton fabFavorite;

    private Restaurant restaurant;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgRestaurant = findViewById(R.id.img_restaurant);
        tvName = findViewById(R.id.tv_name);
        tvAddress = findViewById(R.id.tv_address);
        tvCity = findViewById(R.id.tv_city);
        tvDescription = findViewById(R.id.tv_description);
        ratingBar = findViewById(R.id.rating_bar);
        fabFavorite = findViewById(R.id.fab_favorite);
        tvFoodMenu = findViewById(R.id.tv_food_menu);
        tvDrinkMenu = findViewById(R.id.tv_drink_menu);

        String restaurantId = getIntent().getStringExtra("restaurant_id");
        if (restaurantId == null) {
            Toast.makeText(this, "Restaurant ID not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<RestaurantDetailResponse> call = apiInterface.getRestaurantDetail(restaurantId);
        call.enqueue(new Callback<RestaurantDetailResponse>() {
            @Override
            public void onResponse(Call<RestaurantDetailResponse> call, Response<RestaurantDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    restaurant = response.body().getRestaurant();
                    displayRestaurantDetail();
                } else {
                    loadFromDatabase(dbHelper, restaurantId);
                }
            }

            @Override
            public void onFailure(Call<RestaurantDetailResponse> call, Throwable t) {
                loadFromDatabase(dbHelper, restaurantId);
            }
        });

        fabFavorite.setOnClickListener(v -> {
            if (restaurant != null) {
                if (isFavorite) {
                    dbHelper.deleteFavorite(restaurant.getId());
                    Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Favorite favorite = new Favorite(
                            restaurant.getId(),
                            restaurant.getName(),
                            restaurant.getDescription(),
                            restaurant.getPictureId(),
                            restaurant.getCity(),
                            restaurant.getRating()
                    );

                    if (restaurant.getMenus() != null) {
                        favorite.setFoodMenu(convertMenuListToString(restaurant.getMenus().getFoods()));
                        favorite.setDrinkMenu(convertMenuListToString(restaurant.getMenus().getDrinks()));
                    }

                    dbHelper.addFavorite(favorite);
                    Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
                }
                isFavorite = !isFavorite;
                updateFavoriteIcon();
            }
        });
    }

    private void loadFromDatabase(DatabaseHelper dbHelper, String restaurantId) {
        try {
            Favorite fav = dbHelper.getFavoriteById(restaurantId);
            if (fav != null) {
                restaurant = new Restaurant(
                        fav.getRestaurantId(),
                        fav.getName(),
                        fav.getDescription(),
                        fav.getPictureId(),
                        fav.getCity(),
                        "", // address kosong
                        fav.getRating(),
                        null
                );
                displayOfflineData(fav);
            } else {
                Toast.makeText(this, "Offline & data not found.", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database error.", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayRestaurantDetail() {
        String imageUrl = "https://restaurant-api.dicoding.dev/images/medium/" + restaurant.getPictureId();
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(imgRestaurant);

        tvName.setText(restaurant.getName());
        tvAddress.setText(restaurant.getAddress());
        tvCity.setText(restaurant.getCity());
        tvDescription.setText(restaurant.getDescription());
        ratingBar.setRating((float) restaurant.getRating());

        StringBuilder foodMenu = new StringBuilder();
        if (restaurant.getMenus() != null && restaurant.getMenus().getFoods() != null) {
            for (Restaurant.Food item : restaurant.getMenus().getFoods()) {
                foodMenu.append("- ").append(item.getName()).append("\n");
            }
        }
        tvFoodMenu.setText(foodMenu.toString());

        StringBuilder drinkMenu = new StringBuilder();
        if (restaurant.getMenus() != null && restaurant.getMenus().getDrinks() != null) {
            for (Restaurant.Drink item : restaurant.getMenus().getDrinks()) {
                drinkMenu.append("- ").append(item.getName()).append("\n");
            }
        }
        tvDrinkMenu.setText(drinkMenu.toString());

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        isFavorite = dbHelper.isFavorite(restaurant.getId());
        updateFavoriteIcon();
    }

    private void displayOfflineData(Favorite fav) {
        String imageUrl = "https://restaurant-api.dicoding.dev/images/medium/" + fav.getPictureId();
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(imgRestaurant);

        tvName.setText(fav.getName());
        tvAddress.setText("-"); // karena tidak tersedia
        tvCity.setText(fav.getCity());
        tvDescription.setText(fav.getDescription());
        ratingBar.setRating((float) fav.getRating());

        tvFoodMenu.setText(convertOfflineMenuToDisplay(fav.getFoodMenu()));
        tvDrinkMenu.setText(convertOfflineMenuToDisplay(fav.getDrinkMenu()));

        isFavorite = true;
        updateFavoriteIcon();
    }

    private String convertMenuListToString(List<? extends Object> items) {
        StringBuilder builder = new StringBuilder();
        for (Object item : items) {
            if (item instanceof Restaurant.Food) {
                builder.append(((Restaurant.Food) item).getName()).append(", ");
            } else if (item instanceof Restaurant.Drink) {
                builder.append(((Restaurant.Drink) item).getName()).append(", ");
            }
        }
        if (builder.length() > 2) {
            builder.setLength(builder.length() - 2);
        }
        return builder.toString();
    }

    private String convertOfflineMenuToDisplay(String menuString) {
        if (menuString == null || menuString.isEmpty()) return "-";
        String[] items = menuString.split(",\\s*");
        StringBuilder display = new StringBuilder();
        for (String item : items) {
            display.append("- ").append(item.trim()).append("\n");
        }
        return display.toString();
    }

    private void updateFavoriteIcon() {
        if (isFavorite) {
            fabFavorite.setImageResource(R.drawable.ic_favorite_filled);
        } else {
            fabFavorite.setImageResource(R.drawable.ic_favorite_border);
        }
    }
}
