package com.mg.hotelmanagementsystem.models;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by moses on 7/11/18.
 */

public class User {

    public static final String WAITER = "waiter";
    public static final String COOK = "cook";
    public static final String CASHIER = "cashier";
    public static final String ADMINISTRATOR = "administrator";

    private String email;
    private String userId;
    private String role;
    private String displayName;
    private String photoUrl;

    public User(){

    }

    public User(FirebaseUser firebaseUser){
        this.setEmail(firebaseUser.getEmail());
        this.setUserId(firebaseUser.getUid());
        this.setRole(WAITER);
        this.setDisplayName(firebaseUser.getDisplayName());
        this.setPhotoUrl(firebaseUser.getPhotoUrl() == null ? "": firebaseUser.getPhotoUrl().toString());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
