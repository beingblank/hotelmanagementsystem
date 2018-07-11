package com.mg.hotelmanagementsystem.models.viewmodels;

import com.mg.hotelmanagementsystem.BR;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.models.Meal;

/**
 * Created by moses on 7/11/18.
 */

public class MealsViewModel extends BaseViewModel<Meal> {
    @Override
    public int getVariableId() {
        return BR.meal;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_meal;
    }
}
