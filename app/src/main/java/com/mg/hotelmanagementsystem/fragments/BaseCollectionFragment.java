package com.mg.hotelmanagementsystem.fragments;

import android.content.Context;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.models.HotelBaseModel;
import com.mg.hotelmanagementsystem.models.viewmodels.RecyclerViewModel;
import com.mg.hotelmanagementsystem.util.Tools;
import com.mg.surblime.ui.ResourceCollectionFragment;

import java.util.ArrayList;

/**
 * Created by moses on 7/13/18.
 */

public abstract class BaseCollectionFragment<T extends RecyclerViewModel<S>, S extends HotelBaseModel> extends ResourceCollectionFragment<T, S> implements ValueEventListener, ChildEventListener {
    private Class<T> viewModelClass;
    private Class<S> modelClass;

    private ArrayList<S> selected = new ArrayList<>();

    public BaseCollectionFragment(Class<T> viewModelClass, Class<S> modelClass) {
        super(viewModelClass);
        this.viewModelClass = viewModelClass;
        this.modelClass = modelClass;
    }

    @Override
    public void loadResource(boolean loadFromCache) {
        setSwipeToRefreshEnabled(false);
        new FirebaseTransaction(getContext(), false)
                .child(getRoot())
                .read(this, false);
        new FirebaseTransaction(getContext(), false)
                .child(getRoot())
                .readChildren(this);
    }

    public abstract String getRoot();

    @Override
    public void onItemClick(View view, S s, int index) {
        select(s);
    }

    private boolean select(S s) {
        if (selectionAllowed()) {
            Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null)
                vibrator.vibrate(50);
            int index = getIndex(s);

            s.toggleSelection();
            if (s.isSelected()) {
                if (isSingleSelection() && !selected.isEmpty()) {
                    S current = selected.get(0);
                    current.setSelected(false);
                    selected.clear();

                    notifyItemChanged(getIndex(current));
                }
                selected.add(s);
            } else {
                selected.remove(s);
            }

            notifyItemChanged(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean onItemLongClick(View view, S s) {
        return select(s);
    }

    public boolean isSingleSelection() {
        return false;
    }

    public abstract boolean selectionAllowed();

    public ArrayList<S> getSelected() {
        return selected;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        try {
            T t = viewModelClass.newInstance();
            t.setUser(Tools.getCurrentUser(getContext()));
            for (DataSnapshot child : dataSnapshot.getChildren()) {
                S item = readValue(child);
                item.setId(child.getKey());
                t.addItem(item);
            }
            onSuccess(t);
        } catch (Exception ignore) {
            onFailure();
        }
    }


    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        S child = readValue(dataSnapshot);
        child.setId(dataSnapshot.getKey());
        if (!getResource().getItems().contains(child)) {
            getResource().addItem(child);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        for (S item : getResource().getItems()) {
            if (item.getId().equals(dataSnapshot.getKey())) {
                int index = getResource().getItems().indexOf(item);
                S newItem = readValue(dataSnapshot);
                if (newItem != null) {
                    newItem.setId(item.getId());
                    getResource().updateItem(newItem, index);
                    notifyItemChanged(index);
                    break;
                }
            }
        }
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        for (S item : getResource().getItems()) {
            if (item.getId().equals(dataSnapshot.getKey())) {
                int index = getResource().getItems().indexOf(item);
                getResource().getItems().remove(index);
                notifyItemRemoved(index);
                break;
            }
        }
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        onFailure();
    }

    public S readValue(DataSnapshot dataSnapshot){
        return dataSnapshot.getValue(modelClass);
    }
}
