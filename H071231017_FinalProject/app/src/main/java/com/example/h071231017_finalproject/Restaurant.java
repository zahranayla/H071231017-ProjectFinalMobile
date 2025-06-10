package com.example.h071231017_finalproject;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Restaurant {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("pictureId")
    private String pictureId;

    @SerializedName("city")
    private String city;

    @SerializedName("rating")
    private double rating;

    @SerializedName("address")
    private String address;

    @SerializedName("categories")
    private List<Category> categories;

    @SerializedName("menus")
    private Menus menus;

    @SerializedName("customerReviews")
    private List<CustomerReview> customerReviews;

    public Restaurant(String id, String name, String description, String pictureId, String city, String address, double rating, List<CustomerReview> customerReviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pictureId = pictureId;
        this.city = city;
        this.address = address;
        this.rating = rating;
        this.customerReviews = customerReviews;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPictureId() { return pictureId; }
    public void setPictureId(String pictureId) { this.pictureId = pictureId; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories) { this.categories = categories; }

    public Menus getMenus() { return menus; }
    public void setMenus(Menus menus) { this.menus = menus; }

    public List<CustomerReview> getCustomerReviews() { return customerReviews; }
    public void setCustomerReviews(List<CustomerReview> customerReviews) { this.customerReviews = customerReviews; }

    public static class Category {
        @SerializedName("name")
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    public static class Menus {
        @SerializedName("foods")
        private List<Food> foods;

        @SerializedName("drinks")
        private List<Drink> drinks;

        public List<Food> getFoods() { return foods; }
        public void setFoods(List<Food> foods) { this.foods = foods; }

        public List<Drink> getDrinks() { return drinks; }
        public void setDrinks(List<Drink> drinks) { this.drinks = drinks; }
    }

    public static class Food {
        @SerializedName("name")
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    public static class Drink {
        @SerializedName("name")
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    public static class CustomerReview {
        @SerializedName("name")
        private String name;

        @SerializedName("review")
        private String review;

        @SerializedName("date")
        private String date;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getReview() { return review; }
        public void setReview(String review) { this.review = review; }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
    }
}
