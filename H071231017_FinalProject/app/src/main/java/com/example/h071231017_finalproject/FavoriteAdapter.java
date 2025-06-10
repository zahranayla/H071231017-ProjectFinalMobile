package com.example.h071231017_finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071231017_finalproject.R;
import com.example.h071231017_finalproject.Favorite;
import com.example.h071231017_finalproject.DetailActivity;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<Favorite> favorites;
    private Context context;

    public FavoriteAdapter(List<Favorite> favorites, Context context) {
        this.favorites = favorites;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Favorite favorite = favorites.get(position);

        holder.tvName.setText(favorite.getName());
        holder.tvCity.setText(favorite.getCity());
        holder.tvDescription.setText(favorite.getDescription());
        holder.ratingBar.setRating((float) favorite.getRating());

        String imageUrl = "https://restaurant-api.dicoding.dev/images/medium/" + favorite.getPictureId();
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.imgRestaurant);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("restaurant_id", favorite.getRestaurantId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public void updateData(List<Favorite> newFavorites) {
        favorites.clear();
        favorites.addAll(newFavorites);
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
