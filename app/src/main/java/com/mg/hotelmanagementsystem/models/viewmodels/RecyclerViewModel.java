package com.mg.hotelmanagementsystem.models.viewmodels;

import com.mg.hotelmanagementsystem.models.User;
import com.mg.surblime.BaseModel;
import com.mg.surblime.ObservableRecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.LayoutManagers;

/**
 * Created by moses on 7/13/18.
 */

public abstract class RecyclerViewModel<T extends BaseModel> extends ObservableRecyclerViewModel<T> {

    private List<T> list = new ArrayList<>();
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public LayoutManagers.LayoutManagerFactory getLayoutManagerFactory(int orientation) {
        return LayoutManagers.linear();
    }

    @Override
    public List<T> getItems() {
        return list;
    }

    @Override
    public void addItems(List<T> items) {
        list.addAll(items);
    }

    @Override
    public void setItems(List<T> items) {
        list = new ArrayList<>(items);
    }

    @Override
    public void addItem(T t) {
        list.add(t);
    }

    @Override
    public void updateItem(T t, int index) {
        list.set(index, t);
    }
}
