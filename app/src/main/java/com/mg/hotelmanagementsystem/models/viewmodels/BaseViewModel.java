package com.mg.hotelmanagementsystem.models.viewmodels;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import com.mg.hotelmanagementsystem.R;

import java.util.ArrayList;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.LayoutManagers;

/**
 * Created by moses on 7/11/18.
 */

public abstract class BaseViewModel<T> {


    public ItemBinding<T> itemBinding;
    public ObservableList<T> items = new ObservableArrayList<>();

    public BaseViewModel() {
        itemBinding = ItemBinding.of(getVariableId(), getLayoutRes());
    }

    public abstract @IdRes
    int getVariableId();

    public abstract @LayoutRes
    int getLayoutRes();

    public LayoutManagers.LayoutManagerFactory getLayoutManagerFactory() {
        return LayoutManagers.linear();
    }


    public @LayoutRes
    int emptyLayout() {
        return R.layout.empty;
    }
}
