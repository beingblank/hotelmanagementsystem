package com.mg.hotelmanagementsystem.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.mg.hotelmanagementsystem.fragments.ChooseMealFragment;
import com.mg.hotelmanagementsystem.fragments.ChooseTableFragment;
import com.mg.hotelmanagementsystem.fragments.OrderSummaryFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

/**
 * Created by moses on 7/22/18.
 */

public class MakeOrderStepperAdapter extends AbstractFragmentStepAdapter {
    public MakeOrderStepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                return new ChooseTableFragment();
            case 1:
                return new ChooseMealFragment();
            case 2:
                return new OrderSummaryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
