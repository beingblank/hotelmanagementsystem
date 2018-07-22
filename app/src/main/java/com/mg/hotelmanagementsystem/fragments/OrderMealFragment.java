package com.mg.hotelmanagementsystem.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.adapters.MakeOrderStepperAdapter;
import com.stepstone.stepper.StepperLayout;

/**
 * Created by moses on 7/22/18.
 */

public class OrderMealFragment extends Fragment {

    private StepperLayout stepperLayout;
    private MakeOrderStepperAdapter makeOrderStepperAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_make_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stepperLayout = view.findViewById(R.id.stepperLayout);

        stepperLayout.setAdapter(makeOrderStepperAdapter = new MakeOrderStepperAdapter(getChildFragmentManager(), getContext()));
    }
}
