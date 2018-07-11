package com.mg.hotelmanagementsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mg.hotelmanagementsystem.database.FirebaseTransaction;
import com.mg.hotelmanagementsystem.database.HotelDatabase;
import com.mg.hotelmanagementsystem.models.User;
import com.mg.hotelmanagementsystem.util.Tools;

import java.util.Collections;
import java.util.List;

/**
 * Created by moses on 7/11/18.
 */

public class WelcomeActivity extends BaseActivity {

    private static final int REQUEST_CODE_SIGN_IN = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        User user = Tools.getCurrentUser(this);
        if (user != null) {
            loginUser(user);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void signIn(View view) {
        List<AuthUI.IdpConfig> providers = Collections.singletonList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(), REQUEST_CODE_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGN_IN) {

            if (resultCode == RESULT_OK) {
                final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                new FirebaseTransaction(this).child("users").child(firebaseUser.getUid()).read(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        if (user == null) {
                            showRoleChooseDialog(firebaseUser);
                        } else {
                            loginUser(user);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } else {
                Tools.alert(this, R.string.dialog_title_sign_in_failed, R.string.dialog_message_sign_in_failed);
            }
        }
    }

    private void showRoleChooseDialog(FirebaseUser firebaseUser) {
        final User user = new User(firebaseUser);
        user.setRole(User.WAITER);
        final String[] roles = getResources().getStringArray(R.array.roles);
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(R.array.roles, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user.setRole(roles[which]);
                    }
                }).setTitle(R.string.dialog_title_choose_role)
                .setPositiveButton(R.string.choose, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        saveUser(user);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    private void saveUser(final User user) {
        new FirebaseTransaction(this)
                .child("users")
                .push(user.getUserId())
                .setValue(user, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        Snackbar.make(findViewById(R.id.mainLayout), R.string.sign_up_successful, Snackbar.LENGTH_LONG).show();
                        new Handler()
                                .postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        new HotelDatabase(WelcomeActivity.this).addUser(user);
                                    }
                                }, 1000);
                    }
                });
    }

    private void loginUser(User user) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
