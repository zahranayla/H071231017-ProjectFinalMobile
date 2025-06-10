package com.example.h071231017_finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> implements Filterable {
    private List<Restaurant> restaurants;
    private List<Restaurant> restaurantsFull;
    private Context context;
    public interface OnEmptyStateListener {
        void onEmpty(boolean isEmpty);
    }

    private OnEmptyStateListener emptyStateListener;

    public void setOnEmptyStateListener(OnEmptyStateListener listener) {
        this.emptyStateListener = listener;
    }

    public RestaurantAdapter(List<Restaurant> restaurants, Context context) {
        this.restaurants = restaurants;
        this.context = context;
        this.restaurantsFull = new ArrayList<>(restaurants);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Restaurant> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(restaurantsFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Restaurant item : restaurantsFull) {
                        if (item.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                restaurants.clear();
                List<Restaurant> filtered = (List<Restaurant>) results.values;
                restaurants.addAll(filtered);
                notifyDataSetChanged();

                if (emptyStateListener != null) {
                    emptyStateListener.onEmpty(filtered.isEmpty());
                }
            }
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        holder.tvName.setText(restaurant.getName());
        holder.tvCity.setText(restaurant.getCity());
        holder.tvDescription.setText(restaurant.getDescription());
        holder.ratingBar.setRating((float) restaurant.getRating());

        String imageUrl = "https://restaurant-api.dicoding.dev/images/medium/" + restaurant.getPictureId();
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.imgRestaurant);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("restaurant_id", restaurant.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void updateData(List<Restaurant> newRestaurants) {
        restaurants.clear();
        restaurants.addAll(newRestaurants);
        restaurantsFull.clear();
        restaurantsFull.addAll(newRestaurants);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCity, tvDescription;
        RatingBar ratingBar;
        ImageView imgRestaurant;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCity = itemView.findViewById(R.id.tv_city);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            imgRestaurant = itemView.findViewById(R.id.img_restaurant);
        }
    }
}
