package com.mg.hotelmanagementsystem.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.mg.hotelmanagementsystem.MakeOrderActivity;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.models.Meal;
import com.mg.hotelmanagementsystem.models.Order;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moses on 7/22/18.
 */

public class ChooseMealFragment extends MealsFragment implements BlockingStep {

    private MakeOrderActivity makeOrderActivity;

    @Override
    public boolean addMeal() {
        return false;
    }

    @Override
    public boolean selectionAllowed() {
        return true;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.title)).setText(R.string.choose_meal);
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        if (getSelected().isEmpty()) {
            Toast.makeText(getContext(), "Select at least one meal", Toast.LENGTH_SHORT).show();
        } else {
            Order order = makeOrderActivity.getOrder();
            order.setOrderId(String.valueOf(Math.abs(order.getTable().hashCode() + order.getMeals().hashCode() + System.currentTimeMillis() % 100)));
            order.getMeals().clear();
            List<Meal> meals = new ArrayList<>();
            for (Meal meal : getSelected()) {
                meals.add(new Meal(meal));
            }

            order.setMeals(meals);
            callback.goToNextStep();
        }

    }

    @Override
    public void onCompleteClicked(final StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.makeOrderActivity = (MakeOrderActivity) activity;
    }
}
