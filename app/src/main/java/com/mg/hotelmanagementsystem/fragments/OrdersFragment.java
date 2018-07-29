package com.mg.hotelmanagementsystem.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mg.hotelmanagementsystem.MakeOrderActivity;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.ViewOrderActivity;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.models.Meal;
import com.mg.hotelmanagementsystem.models.Order;
import com.mg.hotelmanagementsystem.models.Table;
import com.mg.hotelmanagementsystem.models.viewmodels.OrdersViewModel;
import com.mg.hotelmanagementsystem.util.Tools;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by moses on 7/22/18.
 */

public class OrdersFragment extends BaseCollectionFragment<OrdersViewModel, Order> {

    public OrdersFragment() {
        super(OrdersViewModel.class, Order.class);
    }

    @Override
    public String getRoot() {
        return "orders";
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.makeOrderFAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MakeOrderActivity.class));
            }
        });
    }

    @Override
    public void onSuccess(OrdersViewModel ordersViewModel) {
        filterOrders(ordersViewModel);
        super.onSuccess(ordersViewModel);
    }


    private void filterOrders(OrdersViewModel ordersViewModel) {
        ArrayList<Order> orders = new ArrayList<>(ordersViewModel.getItems());
        String[] statuses = Order.getStatuses(getContext());
        ordersViewModel.setItems(new ArrayList<Order>());
        for (Order order : orders) {
            int last = Tools.getCurrentUser(getContext()).isAdmin() ? statuses.length : statuses.length - 1;
            for (int i = 0; i < last; i++) {
                String string = statuses[i];
                if (order.getStatus().equalsIgnoreCase(string)) {
                    ordersViewModel.addItem(order);
                }
            }
        }
    }


    @Override
    public void onItemClick(View view, Order order, int index) {
        Intent intent = new Intent(getContext(), ViewOrderActivity.class);
        intent.putExtra(ViewOrderActivity.BUNDLE_ORDER_ID, order.getId());

        startActivity(intent);
    }

    @Override
    public boolean selectionAllowed() {
        return false;
    }

}
