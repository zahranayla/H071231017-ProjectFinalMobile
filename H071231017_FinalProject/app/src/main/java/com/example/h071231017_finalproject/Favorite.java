package com.example.h071231017_finalproject;

public class Favorite {
    private int id;
    private String restaurantId;
    private String name;
    private String description;
    private String pictureId;
    private String city;
    private double rating;
    private String foodMenu;
    private String drinkMenu;
    public Favorite() {}

    public Favorite(String restaurantId, String name, String description, String pictureId, String city, double rating) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.pictureId = pictureId;
        this.city = city;
        this.rating = rating;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getRestaurantId() { return restaurantId; }
    public void setRestaurantId(String restaurantId) { this.restaurantId = restaurantId; }
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
    public String getFoodMenu() { return foodMenu; }
    public void setFoodMenu(String foodMenu) { this.foodMenu = foodMenu; }

    public String getDrinkMenu() { return drinkMenu; }
    public void setDrinkMenu(String drinkMenu) { this.drinkMenu = drinkMenu; }
}
