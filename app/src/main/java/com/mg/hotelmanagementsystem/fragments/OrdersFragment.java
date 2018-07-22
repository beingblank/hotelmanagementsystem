package com.mg.hotelmanagementsystem.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mg.hotelmanagementsystem.MakeOrderActivity;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.models.Order;
import com.mg.hotelmanagementsystem.models.viewmodels.OrdersViewModel;

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
    public boolean selectionAllowed() {
        return false;
    }
}
