package com.mg.hotelmanagementsystem.models.viewmodels;

import com.mg.hotelmanagementsystem.BR;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.models.Meal;

/**
 * Created by moses on 7/11/18.
 */

public class MealsViewModel extends RecyclerViewModel<Meal> {
    @Override
    public int getVariableId() {
        return BR.meal;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_meal;
    }

    @Override
    public int getItemListenerId() {
        return BR.listener;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_meals;
    }
}
