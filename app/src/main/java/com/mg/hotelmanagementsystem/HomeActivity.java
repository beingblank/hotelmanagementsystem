package com.mg.hotelmanagementsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.mg.hotelmanagementsystem.adapters.MainViewPagerAdapter;
import com.mg.hotelmanagementsystem.database.HotelDatabase;
import com.mg.hotelmanagementsystem.fragments.MealsFragment;
import com.mg.hotelmanagementsystem.fragments.OrdersFragment;
import com.mg.hotelmanagementsystem.fragments.TablesFragment;
import com.mg.hotelmanagementsystem.models.User;
import com.mg.hotelmanagementsystem.util.PushNotificationManager;
import com.mg.hotelmanagementsystem.util.Tools;

/**
 * Created by moses on 7/11/18.
 */

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Toolbar toolbar;
    public DrawerLayout drawer;
    public NavigationView navigationView;

    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;

    private TabLayout tabLayout;

    public User user;
    private String currentFragment = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void init() {
        user = Tools.getCurrentUser(this);
        if (user == null) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }
        setContentView(getContentView());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(Tools.capitalize(user.getRole()));
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setAccountDetails();

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            new HotelDatabase(HomeActivity.this).deleteUser();
            new AlertDialog.Builder(this)
                    .setTitle(R.string.dialog_title_logged_out)
                    .setMessage(R.string.dialog_message_logged_out)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));
                            finish();
                        }
                    }).create().show();
        } else {
            viewPager = findViewById(R.id.viewPager);
            viewPager.setAdapter(mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.main_pages)));

            tabLayout = findViewById(R.id.tabLayout);
            tabLayout.setupWithViewPager(viewPager);
            viewPager.setOffscreenPageLimit(mainViewPagerAdapter.getCount());
        }

        PushNotificationManager.init(this);
    }

    @Override
    public int getStyleTheme() {
        return R.style.AppTheme_Translucent;
    }


    public @LayoutRes
    int getContentView() {
        return R.layout.activity_home;
    }

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
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_logout:
                new HotelDatabase(this).deleteUser();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, WelcomeActivity.class));
                finish();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
