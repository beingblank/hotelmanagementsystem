package com.mg.hotelmanagementsystem;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.mg.hotelmanagementsystem.models.HotelBaseModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by moses on 7/11/18.
 */

public class DataBindingAdapters {

    @BindingAdapter("srcTitle")
    public static void showTextDrawable(ImageView imageView, HotelBaseModel hotelBaseModel) {
        if (hotelBaseModel.isSelected()) {
            TextDrawable drawable = TextDrawable.builder().buildRound("✔", imageView.getContext().getResources().getColor(R.color.md_green_500));
            imageView.setImageDrawable(drawable);
        } else {
            StringBuilder text = new StringBuilder();
            for (String s : hotelBaseModel.getTitle().split("\\s")) {
                if (!s.isEmpty()) {
                    text.append(Character.toUpperCase(s.charAt(0)));
                }
            }
            TextDrawable drawable = TextDrawable.builder().buildRound(text.toString(), ColorGenerator.MATERIAL.getColor(hotelBaseModel.getId()));
            imageView.setImageDrawable(drawable);
        }
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
    public static void setFoodType(ImageView imageView, String category) {
        TextDrawable drawable = TextDrawable.builder().buildRoundRect(category, ColorGenerator.MATERIAL.getColor(category), 5);
        imageView.setImageDrawable(drawable);
    }
}
