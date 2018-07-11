package com.mg.hotelmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mg.hotelmanagementsystem.database.HotelDatabase;
import com.mg.hotelmanagementsystem.models.User;

/**
 * Created by moses on 7/11/18.
 */

public abstract class BaseHomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Toolbar toolbar;
    public DrawerLayout drawer;
    public NavigationView navigationView;

    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = new HotelDatabase(this).getUser();
        if (user == null) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setAccountDetails();
    }

    public abstract @LayoutRes
    int getContentView();

    private void setAccountDetails() {
        View view = navigationView.getHeaderView(0);
        ImageView imageView = view.findViewById(R.id.profilePhotoImageView);
        TextView displayName = view.findViewById(R.id.displayNameTextView);
        TextView email = view.findViewById(R.id.emailTextView);

        Glide.with(this).load(user.getPhotoUrl()).into(imageView);
        displayName.setText(user.getDisplayName());
        email.setText(user.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
