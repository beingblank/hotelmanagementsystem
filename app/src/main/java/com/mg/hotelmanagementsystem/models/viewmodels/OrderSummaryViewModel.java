package com.mg.hotelmanagementsystem.models.viewmodels;

import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.models.Order;
import com.mg.surblime.SingleViewModel;

/**
 * Created by moses on 7/23/18.
 */

public class OrderSummaryViewModel extends SingleViewModel<Order> {
    public MealsViewModel mealsViewModel = new MealsViewModel();

    public OrderSummaryViewModel() {
        super();
        model = new Order();
    }

    public OrderSummaryViewModel(int status) {
        super(status);
        model = new Order();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_order_summary;
    }

}
