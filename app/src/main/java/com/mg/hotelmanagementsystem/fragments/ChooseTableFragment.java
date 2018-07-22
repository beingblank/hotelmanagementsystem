package com.mg.hotelmanagementsystem.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.models.Table;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

/**
 * Created by moses on 7/22/18.
 */

public class ChooseTableFragment extends TablesFragment implements Step {

    private boolean selection = false;

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.title)).setText(R.string.choose_table);
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public boolean isSingleSelection() {
        return true;
    }

    @Override
    public boolean selectionAllowed() {
        return true;
    }
}
