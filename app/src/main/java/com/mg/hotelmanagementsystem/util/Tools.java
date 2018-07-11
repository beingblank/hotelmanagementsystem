package com.mg.hotelmanagementsystem.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.mg.hotelmanagementsystem.database.HotelDatabase;
import com.mg.hotelmanagementsystem.models.User;

/**
 * Created by moses on 7/11/18.
 */

public class Tools {

    private static User user;

    public static void alert(Context context, int titleId, int messageId) {
        alert(context, context.getString(titleId), context.getString(messageId));
    }

    public static void alert(Context context, String title, String message) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message).create().show();
    }

    public static User getCurrentUser(Context context) {
        if (user == null) {
            user = new HotelDatabase(context).getUser();
        }
        return user;
    }

    public static String capitalize(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        }
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public interface Preferences {
    }
}
