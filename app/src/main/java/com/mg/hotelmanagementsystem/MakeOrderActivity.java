package com.mg.hotelmanagementsystem;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mg.hotelmanagementsystem.adapters.MakeOrderStepperAdapter;
import com.mg.surblime.activities.ToolbarActivity;
import com.stepstone.stepper.StepperLayout;

/**
 * Created by moses on 7/22/18.
 */

public class MakeOrderActivity extends ToolbarActivity {

    private MakeOrderStepperAdapter makeOrderStepperAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StepperLayout stepperLayout = findViewById(R.id.stepperLayout);

        stepperLayout.setAdapter(makeOrderStepperAdapter = new MakeOrderStepperAdapter(getSupportFragmentManager(), this));
        setDisplayHomeAsUpEnabled(true);
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
}
