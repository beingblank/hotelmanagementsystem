package com.mg.hotelmanagementsystem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.mg.hotelmanagementsystem.adapters.MakeOrderStepperAdapter;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.models.Order;
import com.mg.surblime.activities.ToolbarActivity;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

/**
 * Created by moses on 7/22/18.
 */

public class MakeOrderActivity extends ToolbarActivity implements StepperLayout.StepperListener {

    private MakeOrderStepperAdapter makeOrderStepperAdapter;
    private Order order;
    private StepperLayout stepperLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stepperLayout = findViewById(R.id.stepperLayout);

        stepperLayout.setAdapter(makeOrderStepperAdapter = new MakeOrderStepperAdapter(getSupportFragmentManager(), this));
        setDisplayHomeAsUpEnabled(true);
        stepperLayout.setListener(this);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_make_order);
    }

    @Override
    public int toolbarId() {
        return R.id.toolbar;
    }

    @Override
    public int getStyleTheme() {
        return R.style.AppTheme_NoActionBar;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public void onBackPressed() {
        int index = stepperLayout.getCurrentStepPosition();
        if (index == 0) {
            super.onBackPressed();
        } else {
            stepperLayout.setCurrentStepPosition(index - 1);
        }
    }

    @Override
    public void onCompleted(View completeButton) {
        order.setOrderDate(System.currentTimeMillis());
        new FirebaseTransaction(this)
                .child("orders")
                .push()
                .setValue(getOrder(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        finish();
                    }
                });
    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {

    }
}
