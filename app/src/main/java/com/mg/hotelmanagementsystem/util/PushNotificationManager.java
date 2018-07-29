package com.mg.hotelmanagementsystem.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.mg.hotelmanagementsystem.HomeActivity;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.models.Order;
import com.mg.hotelmanagementsystem.models.User;

/**
 * Created by moses on 7/29/18.
 */

public class PushNotificationManager {

    public static void init(final Context context) {
        new FirebaseTransaction(context, false)
                .child("orders")
                .readChildren(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Order order = dataSnapshot.getValue(Order.class);
                        User user = Tools.getCurrentUser(context);
                        if (user != null && (user.isAdmin() || user.isCook())) {
                            notification("New Order", "A new order has been placed!!", context);
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Order order = dataSnapshot.getValue(Order.class);
                        User user = Tools.getCurrentUser(context);
                        switch (order.getStatus()) {
                            case Order.STATUS_KITCHEN:
                                if (user != null && user.isAdmin()) {
                                    notification("Order in kitchen", "Order " + order.getTitle() + " is now being cooked.", context);
                                }
                                break;
                            case Order.STATUS_READY:
                                if (user != null && (user.isAdmin() && user.isWaiter())) {
                                    notification("Order Ready", "Order " + order.getTitle() + " is ready", context);
                                }
                                break;
                            case Order.STATUS_SERVED:
                                if (user != null && user.isAdmin() && user.isCashier()) {
                                    notification("Order Served", "Order " + order.getTitle() + " has been served", context);
                                }
                                break;
                            case Order.STATUS_PAID:
                                if (user != null && user.isAdmin()) {
                                    notification("Order Paid", "Order " + order.getTitle() + " has been paid!", context);
                                }
                                break;
                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private static void notification(String title, String message, Context context) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, HomeActivity.class), 0))
                .setChannelId("hotel_system");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("hotel_system", "Hotel System", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(1, builder.build());
    }
}
