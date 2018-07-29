package com.mg.hotelmanagementsystem.models;

import android.content.Context;

import com.mg.hotelmanagementsystem.util.Tools;

import java.util.ArrayList;
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

    private String orderId = "";
    private Table table = new Table();
    private List<Meal> meals = new ArrayList<>();
    private long orderDate;
    private long servedAt;
    private long paidAt;
    private boolean paid = false;
    private boolean mealsReady = false;
    private boolean served = false;
    private String status = Order.STATUS_PENDING;

    public Order() {

    }

    @Override
    public String getTitle() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public static String[] getStatuses(Context context) {
        final String[] statuses;

        switch (Tools.getCurrentUser(context).getRole()) {
            case User.ADMINISTRATOR:
                statuses = new String[]{Order.STATUS_PENDING, Order.STATUS_KITCHEN, Order.STATUS_READY, Order.STATUS_SERVED, Order.STATUS_PAID};
                break;
            case User.WAITER:
                statuses = new String[]{Order.STATUS_PENDING, Order.STATUS_READY, Order.STATUS_SERVED};
                break;
            case User.COOK:
                statuses = new String[]{Order.STATUS_PENDING, Order.STATUS_KITCHEN, Order.STATUS_READY};
                break;
            default:
                statuses = new String[]{Order.STATUS_SERVED, Order.STATUS_PAID};
                break;
        }
        return statuses;
    }

}
