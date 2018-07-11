package com.mg.hotelmanagementsystem.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by moses on 7/11/18.
 */

public class Tools {

    public static void alert(Context context, int titleId, int messageId) {
        alert(context, context.getString(titleId), context.getString(messageId));
    }

    public static void alert(Context context, String title, String message) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(message).create().show();
    }
}
