package com.mg.hotelmanagementsystem.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by moses on 7/11/18.
 */

public class FirebaseTransaction {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ProgressDialog progressDialog;


    public FirebaseTransaction(Context context) {
        this(context, "", "Loading...");
    }

    public FirebaseTransaction(Context context, String dialogTitle, String dialogMessage) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(dialogTitle);
        progressDialog.setMessage(dialogMessage);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public FirebaseTransaction child(String childName) {
        databaseReference = databaseReference.child(childName);
        return this;
    }

    public void setValue(Object value) {
        setValue(value, null);
    }

    public void setValue(Object value, final DatabaseReference.CompletionListener completionListener) {
        databaseReference.setValue(value, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                progressDialog.dismiss();
                completionListener.onComplete(databaseError, databaseReference);
            }
        });
    }

    public void setValue(Object value, DatabaseReference.CompletionListener completionListener, ValueEventListener valueEventListener) {
        read(valueEventListener);
        setValue(value, completionListener);
    }

    public void read(final ValueEventListener valueEventListener) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                valueEventListener.onDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                valueEventListener.onCancelled(databaseError);
            }
        });
    }

}
