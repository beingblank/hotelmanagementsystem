package com.mg.hotelmanagementsystem.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mg.hotelmanagementsystem.MakeOrderActivity;
import com.mg.hotelmanagementsystem.models.Order;
import com.mg.hotelmanagementsystem.models.viewmodels.OrderSummaryViewModel;
import com.mg.surblime.ui.ResourceFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

/**
 * Created by moses on 7/23/18.
 */

public class OrderSummaryFragment extends ResourceFragment<OrderSummaryViewModel, Order> implements Step {

    private MakeOrderActivity makeOrderActivity;

    public OrderSummaryFragment() {
        super(OrderSummaryViewModel.class);
    }

    @Override
    public void loadResource(boolean loadFromCache) {
    }

    @Override
    public void onSuccess(OrderSummaryViewModel orderSummaryViewModel) {
        super.onSuccess(orderSummaryViewModel);
        setSwipeToRefreshEnabled(false);
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        OrderSummaryViewModel orderSummaryViewModel = new OrderSummaryViewModel();
        orderSummaryViewModel.setModel(makeOrderActivity.getOrder());
        onSuccess(orderSummaryViewModel);
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        makeOrderActivity = (MakeOrderActivity) context;
    }
}
