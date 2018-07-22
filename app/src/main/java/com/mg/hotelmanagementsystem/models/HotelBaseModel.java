package com.mg.hotelmanagementsystem.models;

import com.j256.ormlite.field.DatabaseField;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 7/13/18.
 */

public abstract class HotelBaseModel extends BaseModel {

    @DatabaseField(id = true)
    private String id;
    private User user;
    private boolean selected;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HotelBaseModel) {
            return ((HotelBaseModel) obj).getId().equals(getId());
        }
        return super.equals(obj);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void toggleSelection() {
        this.selected = !this.isSelected();
    }

    public abstract String getTitle();
}
