package com.mg.hotelmanagementsystem.models;

import com.j256.ormlite.field.DatabaseField;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 7/13/18.
 */

public class HotelBaseModel extends BaseModel {

    @DatabaseField(id = true)
    private String id;
    private User user;

    public String getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setId(String id) {
        this.id = id;
    }
}
