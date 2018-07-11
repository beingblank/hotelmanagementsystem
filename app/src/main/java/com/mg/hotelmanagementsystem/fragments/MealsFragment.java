package com.mg.hotelmanagementsystem.fragments;

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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moses on 7/11/18.
 */

public class MealsFragment extends BaseFragment {


    private FragmentMealsBinding fragmentMealsBinding;
    private MealsViewModel mealsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMealsBinding = FragmentMealsBinding.inflate(inflater, container, false);

        return fragmentMealsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.addMealFAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddMealDialog().show(getChildFragmentManager(), "add_meal_dialog");
            }
        });
        mealsViewModel = new MealsViewModel();
        fragmentMealsBinding.setMealViewModel(mealsViewModel);
        syncMeals();
    }

    private void bind() {
        fragmentMealsBinding.setVariable(BR.mealViewModel, mealsViewModel);
        fragmentMealsBinding.executePendingBindings();
    }

    private void syncMeals() {
        new FirebaseTransaction(getContext())
                .child("meals").read(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (mealsViewModel.items.isEmpty()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        mealsViewModel.items.add(child.getValue(Meal.class));
                    }
                    bind();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        new FirebaseTransaction(getContext(), false)
                .child("meals").readChildren(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mealsViewModel.items.add(dataSnapshot.getValue(Meal.class));
                bind();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
}
