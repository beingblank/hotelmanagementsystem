package com.mg.hotelmanagementsystem.fragments;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mg.hotelmanagementsystem.BR;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.databinding.FragmentMealsBinding;
import com.mg.hotelmanagementsystem.dialogs.AddMealDialog;
import com.mg.hotelmanagementsystem.models.Meal;
import com.mg.hotelmanagementsystem.models.viewmodels.MealsViewModel;
import com.mg.surblime.ui.ResourceCollectionFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moses on 7/11/18.
 */

public class MealsFragment extends BaseCollectionFragment<MealsViewModel, Meal> {

    public MealsFragment() {
        super(MealsViewModel.class, Meal.class);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewById(R.id.addMealFAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddMealDialog().show(getChildFragmentManager(), "add_meal_dialog");
            }
        });
    }

    @Override
    public void loadResource(boolean loadFromCache) {
        super.loadResource(loadFromCache);
        setSwipeToRefreshEnabled(false);
    }


    @Override
    public void onSuccess(MealsViewModel mealsViewModel) {
        super.onSuccess(mealsViewModel);
        findViewById(R.id.addMealFAB).setVisibility(addMeal() ? View.VISIBLE : View.GONE);
    }

    public boolean addMeal() {
        return true;
    }

    @Override
    public String getRoot() {
        return "meals";
    }

    @Override
    public boolean selectionAllowed() {
        return false;
    }
}
