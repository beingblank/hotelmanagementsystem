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

    public String email;
    public String userId;
    public String role;
    public String displayName;
    public String photoUrl;
}
