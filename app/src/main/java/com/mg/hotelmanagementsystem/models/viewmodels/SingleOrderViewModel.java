package com.mg.hotelmanagementsystem.models.viewmodels;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.models.Order;
import com.mg.hotelmanagementsystem.models.OrderActivity;

/**
 * Created by moses on 7/29/18.
 */

public class SingleOrderViewModel extends OrderSummaryViewModel {

    public OrderActivityViewModel orderActivityViewModel = new OrderActivityViewModel();

    @Override
    public void setModel(Order model) {
        orderActivityViewModel = new OrderActivityViewModel();
        if (model.getOrderDate() != 0) {
            orderActivityViewModel.addItem(new OrderActivity(R.drawable.pending, "The order was made " + TimeAgo.using(model.getOrderDate())));
        }

        if (model.getServedAt() != 0) {
            orderActivityViewModel.addItem(new OrderActivity(R.drawable.served, "The meals were served " + TimeAgo.using(model.getServedAt())));
        }

        if (model.getPaidAt() != 0) {
            orderActivityViewModel.addItem(new OrderActivity(R.drawable.paid, "The order was paid " + TimeAgo.using(model.getPaidAt())));
        }
        super.setModel(model);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_view_order;
    }
}
