package com.excavator.m2m.campus_ieum;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by Beginner on 2018. 1. 18..
 */

public class Font extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/KoPubDotumMedium.ttf"))
                .addBold(Typekit.createFromAsset(this, "fonts/KoPubDotumBold.ttf"));
    }
}
