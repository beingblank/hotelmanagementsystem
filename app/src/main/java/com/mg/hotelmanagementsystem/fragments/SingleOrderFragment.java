package com.mg.hotelmanagementsystem.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.ViewOrderActivity;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.models.Order;
import com.mg.hotelmanagementsystem.models.viewmodels.SingleOrderViewModel;
import com.mg.surblime.ui.ResourceFragment;


/**
 * Created by moses on 7/29/18.
 */

public class SingleOrderFragment extends ResourceFragment<SingleOrderViewModel, Order> {

    private Button orderStatusButton;

    public SingleOrderFragment() {
        super(SingleOrderViewModel.class);
    }


    @Override
    public void loadResource(boolean loadFromCache) {
        new FirebaseTransaction(getContext(), false)
                .child("orders")
                .child(getActivity().getIntent().getStringExtra(ViewOrderActivity.BUNDLE_ORDER_ID))
                .read(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Order order = dataSnapshot.getValue(Order.class);
                        order.setId(dataSnapshot.getKey());
                        SingleOrderViewModel singleOrderViewModel = new SingleOrderViewModel();
                        singleOrderViewModel.setModel(order);
                        onSuccess(singleOrderViewModel);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onSuccess(SingleOrderViewModel singleOrderViewModel) {
        super.onSuccess(singleOrderViewModel);
        orderStatusButton = (Button) findViewById(R.id.orderStatusButton);
        updateOrderStatus(singleOrderViewModel.model);
    }

    @SuppressLint("SetTextI18n")
    private void updateOrderStatus(final Order order) {
        String[] allStatuses = new String[]{Order.STATUS_PENDING, Order.STATUS_KITCHEN, Order.STATUS_READY, Order.STATUS_SERVED, Order.STATUS_PAID};
        String[] statuses = Order.getStatuses(getContext());
        int index = 0;
        for (String status : statuses) {
            index++;
            if (order.getStatus().equalsIgnoreCase(status)) {
                break;
            }
        }

        if (index == statuses.length) {
            orderStatusButton.setVisibility(View.GONE);
            return;
        }
        int currentIndex = 0;
        for (String status : allStatuses) {
            if (statuses[index].equalsIgnoreCase(status)) {
                break;
            }
            currentIndex++;
        }

        if (!allStatuses[currentIndex - 1].equalsIgnoreCase(order.getStatus())) {
            orderStatusButton.setVisibility(View.GONE);
        } else {

            final String nextStatus = statuses[index];
            if (nextStatus.equalsIgnoreCase(Order.STATUS_SERVED)) {
                order.setServedAt(System.currentTimeMillis());
            }
            if (nextStatus.equalsIgnoreCase(Order.STATUS_PAID)) {

                order.setPaidAt(System.currentTimeMillis());
            }
            orderStatusButton.setText("Set as " + nextStatus);
            orderStatusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Set as " + nextStatus)
                            .setMessage("Do you want to update the order status?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    order.setStatus(nextStatus);
                                    updateOrder(order);
                                }
                            }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create().show();
                }
            });
        }
    }

    private void updateOrder(final Order order) {
        new FirebaseTransaction(getContext())
                .child("orders")
                .child(order.getId())
                .setValue(order);
    }

}
