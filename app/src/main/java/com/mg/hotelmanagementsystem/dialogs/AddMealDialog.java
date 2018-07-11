package com.mg.hotelmanagementsystem.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.models.Meal;
import com.mg.hotelmanagementsystem.util.Tools;

/**
 * Created by moses on 7/11/18.
 */

public class AddMealDialog extends BaseDialog {

    private TextInputLayout mealNameEditText;
    private TextInputLayout mealPriceEditText;
    private AppCompatSpinner mealTypeSpinner;
    private Button cancelButton;
    private Button saveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mealNameEditText = view.findViewById(R.id.mealNameEditText);
        mealPriceEditText = view.findViewById(R.id.priceEditText);
        mealTypeSpinner = view.findViewById(R.id.mealTypeSpinner);
        cancelButton = view.findViewById(R.id.cancelButton);
        saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMeal();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private boolean validate() {
        boolean validate = true;
        if (mealNameEditText.getEditText().getText().toString().isEmpty()) {
            mealNameEditText.setError("Meal name cannot be empty");
            validate = false;
        }
        if (mealPriceEditText.getEditText().getText().toString().isEmpty()) {
            mealPriceEditText.setError("Meal price cannot be empty");
            validate = false;
        }

        return validate;
    }

    private void saveMeal() {
        if (validate()) {
            getDialog().dismiss();
            Meal meal = new Meal();
            meal.setCategory(mealTypeSpinner.getSelectedItem().toString());
            meal.setMealName(mealNameEditText.getEditText().getText().toString());
            meal.setPrice(Double.parseDouble(mealPriceEditText.getEditText().getText().toString()));

            new FirebaseTransaction(getContext())
                    .child("meals")
                    .push()
                    .setValue(meal, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            Tools.alert(getContext(), R.string.dialog_title_add_meal, R.string.dialog_message_add_meal);
                        }
                    });

        }
    }
}
