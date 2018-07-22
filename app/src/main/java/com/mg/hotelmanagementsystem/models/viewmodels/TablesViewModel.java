package com.mg.hotelmanagementsystem.models.viewmodels;

import com.mg.hotelmanagementsystem.BR;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.models.Table;

/**
 * Created by moses on 7/12/18.
 */

public class TablesViewModel extends RecyclerViewModel<Table> {
    @Override
    public int getVariableId() {
        return BR.table;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_table;
    }

    @Override
    public int getItemListenerId() {
        return BR.listener;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_tables;
    }
}
