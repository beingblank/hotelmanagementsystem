package com.mg.hotelmanagementsystem.fragments;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mg.hotelmanagementsystem.BR;
import com.mg.hotelmanagementsystem.R;
import com.mg.hotelmanagementsystem.databinding.FragmentTablesBinding;
import com.mg.hotelmanagementsystem.dialogs.AddTableDialog;
import com.mg.hotelmanagementsystem.models.Table;
import com.mg.hotelmanagementsystem.models.viewmodels.TablesViewModel;

/**
 * Created by moses on 7/12/18.
 */

public class TablesFragment extends BaseCollectionFragment<TablesViewModel, Table> {
    public TablesFragment() {
        super(TablesViewModel.class, Table.class);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewById(R.id.addTableFAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddTableDialog().show(getChildFragmentManager(), "add_table_dialog");
            }
        });
    }

    @Override
    public void onSuccess(TablesViewModel tablesViewModel) {
        super.onSuccess(tablesViewModel);
        findViewById(R.id.addTableFAB).setVisibility(addTable() ? View.VISIBLE : View.GONE);
    }

    public boolean addTable() {
        return true;
    }

    @Override
    public String getRoot() {
        return "tables";
    }

    @Override
    public boolean selectionAllowed() {
        return false;
    }
}
