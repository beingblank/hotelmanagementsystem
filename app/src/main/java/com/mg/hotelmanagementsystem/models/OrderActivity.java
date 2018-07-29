package com.mg.hotelmanagementsystem.models;

import android.support.annotation.IdRes;

import com.mg.surblime.BaseModel;

/**
 * Created by moses on 7/29/18.
 */

public class OrderActivity extends BaseModel {
    public @IdRes
    int icon;
    public String message;

    public OrderActivity(int icon, String message) {
        this.icon = icon;
        this.message = message;
    }
}
