package com.mg.hotelmanagementsystem.fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.models.HotelBaseModel;
import com.mg.hotelmanagementsystem.models.viewmodels.RecyclerViewModel;
import com.mg.hotelmanagementsystem.util.Tools;
import com.mg.surblime.ui.ResourceCollectionFragment;

/**
 * Created by moses on 7/13/18.
 */

public abstract class BaseCollectionFragment<T extends RecyclerViewModel<S>, S extends HotelBaseModel> extends ResourceCollectionFragment<T, S> {
    private Class<T> viewModelClass;
    private Class<S> modelClass;

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
                .read(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            T t = viewModelClass.newInstance();
                            t.setUser(Tools.getCurrentUser(getContext()));
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                S item = child.getValue(modelClass);
                                item.setId(child.getKey());
                                t.addItem(item);
                            }
                            onSuccess(t);
                        } catch (Exception ignore) {
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        onFailure();
                    }
                }, false);
        new FirebaseTransaction(getContext(), false)
                .child(getRoot())
                .readChildren(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        getResource().addItem(dataSnapshot.getValue(modelClass));
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        for (S item : getResource().getItems()) {
                            if (item.getId().equals(dataSnapshot.getKey())) {
                                int index = getResource().getItems().indexOf(item);
                                S newItem = dataSnapshot.getValue(modelClass);
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
                });
    }

    public abstract String getRoot();
}
