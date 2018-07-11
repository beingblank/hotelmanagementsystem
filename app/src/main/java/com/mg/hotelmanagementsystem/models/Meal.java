package com.mg.hotelmanagementsystem.models;

/**
 * Created by moses on 7/11/18.
 */

public class Meal {

    public static final String SNACK = "snack";
    public static final String DRINK = "drink";
    public static final String FOOD = "food";

    private String mealId;
    private String mealName;
    private double price;
    private String category;

    public Meal() {

    }

    public Meal(String mealId, String mealName, double price, String category) {
        this.setMealId(mealId);
        this.setMealName(mealName);
        this.setPrice(price);
        this.setCategory(category);
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
