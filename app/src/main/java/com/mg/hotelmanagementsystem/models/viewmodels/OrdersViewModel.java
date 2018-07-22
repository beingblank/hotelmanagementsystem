package com.mg.hotelmanagementsystem.models.viewmodels;

import com.mg.hotelmanagementsystem.BR;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.models.Order;

/**
 * Created by moses on 7/22/18.
 */

public class OrdersViewModel extends RecyclerViewModel<Order> {
    @Override
    public int getVariableId() {
        return BR.order;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_order;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_orders;
    }

    @Override
    public int getItemListenerId() {
        return BR.listener;
    }
}
