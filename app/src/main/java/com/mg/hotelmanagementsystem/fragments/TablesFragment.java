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
    public String getRoot() {
        return "tables";
    }

    @Override
    public boolean selectionAllowed() {
        return false;
    }
}
