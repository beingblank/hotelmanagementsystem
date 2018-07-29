package com.mg.hotelmanagementsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mg.surblime.activities.ToolbarActivity;

/**
 * Created by moses on 7/29/18.
 */

public class ViewOrderActivity extends ToolbarActivity {

    public static final String BUNDLE_ORDER_ID = "order_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_view_order);
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
