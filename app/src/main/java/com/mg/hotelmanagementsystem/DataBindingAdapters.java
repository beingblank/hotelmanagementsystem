package com.mg.hotelmanagementsystem;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

/**
 * Created by moses on 7/11/18.
 */

public class DataBindingAdapters {

    @BindingAdapter("srcTitle")
    public static void showTextDrawable(ImageView imageView, String title) {
        TextDrawable drawable = TextDrawable.builder().buildRound(String.valueOf(title.charAt(0)), ColorGenerator.MATERIAL.getRandomColor());
        imageView.setImageDrawable(drawable);
    }

    @SuppressLint("DefaultLocale")
    @BindingAdapter("price")
    public static void showPrice(TextView textView, double price) {
        textView.setText(String.format("Ksh. %d", (int) price));
    }

    @BindingAdapter("layout")
    public static void setLayout(FrameLayout frameLayout, @LayoutRes int layoutRes) {
        if (layoutRes != 0) {
            frameLayout.removeAllViews();
            LayoutInflater layoutInflater = LayoutInflater.from(frameLayout.getContext());
            View child = layoutInflater.inflate(layoutRes, null);
            child.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            frameLayout.addView(child);
        }
    }

    @BindingAdapter("mealCategory")
    public static void setFoodType(TextView textView, String category) {
        textView.setTextColor(ColorGenerator.MATERIAL.getColor(category));
        textView.setText(category);
    }
}
