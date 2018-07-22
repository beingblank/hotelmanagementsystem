package com.mg.hotelmanagementsystem.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by moses on 7/22/18.
 */

public class Order extends HotelBaseModel {

    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_KITCHEN = "Cooking";
    public static final String STATUS_READY = "Ready";
    public static final String STATUS_SERVED = "Served";
    public static final String STATUS_PAID = "Paid";

    private String title;
    private Table table;
    private List<Meal> meals = new ArrayList<>();
    private long orderDate;
    private long servedAt;
    private long paidAt;
    private boolean paid = false;
    private boolean mealsReady = false;
    private boolean served = false;
    private String status = Order.STATUS_PENDING;


    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", getTitle());
        map.put("table", table.getId());
        map.put("orderDate", getOrderDate());
        map.put("paid", isPaid());
        map.put("mealsReady", isMealsReady());
        map.put("served", isServed());
        map.put("servedAt", getServedAt());
        map.put("status", getStatus());
        map.put("paidAt", getPaidAt());
        List<String> meals = new ArrayList<>();
        for (Meal meal : this.meals) {
            meals.add(meal.getId());
        }
        map.put("meals", meals);

        return map;
    }

    public Order(HashMap<String, Object> map) {
        setTitle((String) map.get("title"));
        setTable((Table) map.get("table"));
        setOrderDate((Long) map.get("orderDate"));
        setPaid((boolean) map.get("paid"));
        setMealsReady((boolean) map.get("mealsReady"));
        setServed((boolean) map.get("served"));
        setMeals((List<Meal>) map.get("meals"));
        setPaidAt((Long) map.get("paidAt"));
        setServedAt((Long) map.get("servedAt"));
        setStatus((String) map.get("status"));
    }

    public Order() {

    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isMealsReady() {
        return mealsReady;
    }

    public void setMealsReady(boolean mealsReady) {
        this.mealsReady = mealsReady;
    }

    public boolean isServed() {
        return served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

    public long getServedAt() {
        return servedAt;
    }

    public void setServedAt(long servedAt) {
        this.servedAt = servedAt;
    }

    public long getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(long paidAt) {
        this.paidAt = paidAt;
    }

    public int getTotal() {
        int total = 0;
        for (Meal meal : meals) {
            total += meal.getPrice();
        }
        return total;
    }
}
