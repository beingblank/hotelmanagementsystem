package com.mg.hotelmanagementsystem.models;

import com.google.firebase.auth.FirebaseUser;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 7/11/18.
 */

@DatabaseTable(tableName = "users")
public class User extends HotelBaseModel {

    public static final String WAITER = "waiter";
    public static final String COOK = "cook";
    public static final String CASHIER = "cashier";
    public static final String ADMINISTRATOR = "administrator";

    @DatabaseField
    private String email;
    @DatabaseField
    private String role;
    @DatabaseField
    private String displayName;
    @DatabaseField
    private String photoUrl;

    public User() {

    }

    public User(FirebaseUser firebaseUser) {
        this(firebaseUser, "");
    }

    public User(FirebaseUser firebaseUser, String role) {
        this.setEmail(firebaseUser.getEmail());
        this.setId(firebaseUser.getUid());
        this.setRole(role);
        this.setDisplayName(firebaseUser.getDisplayName());
        this.setPhotoUrl(firebaseUser.getPhotoUrl() == null ? "" : firebaseUser.getPhotoUrl().toString());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public boolean isAdmin() {
        return role.equals(User.ADMINISTRATOR);
    }

    public boolean isWaiter() {
        return role.equals(User.WAITER);
    }

    public boolean isCook() {
        return role.equals(User.COOK);
    }

    public boolean isCashier() {
        return role.equals(User.CASHIER);
    }
}
