package com.mg.hotelmanagementsystem.models.viewmodels;

import com.mg.hotelmanagementsystem.BR;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.models.OrderActivity;

/**
 * Created by moses on 7/29/18.
 */

public class OrderActivityViewModel extends RecyclerViewModel<OrderActivity> {
    @Override
    public int getVariableId() {
        return BR.orderActivity;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_order_activity;
    }

    @Override
    public int getItemListenerId() {
        return 0;
    }
}
