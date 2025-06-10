package com.example.h071231017_finalproject;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RestaurantListResponse {
    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("count")
    private int count;

    @SerializedName("restaurants")
    private List<Restaurant> restaurants;

    public boolean isError() { return error; }
    public void setError(boolean error) { this.error = error; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
    public List<Restaurant> getRestaurants() { return restaurants; }
    public void setRestaurants(List<Restaurant> restaurants) { this.restaurants = restaurants; }
}
