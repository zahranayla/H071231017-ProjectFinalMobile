package com.example.h071231017_finalproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("list")
    Call<RestaurantListResponse> getRestaurants();

    @GET("detail/{id}")
    Call<RestaurantDetailResponse> getRestaurantDetail(@Path("id") String id);
}
