package com.excavator.m2m.campus_ieum;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by Beginner on 2018. 1. 29..
 */

public class CustomDialogReservComplete extends DialogFragment implements View.OnClickListener {
    private Fragment fm;

    String mTxt;
    String mTxt2;

    public CustomDialogReservComplete() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_car_reserv_complete, container);

        ConstraintLayout cl = v.findViewById(R.id.alertReservComplete_sub);
        cl.setOnClickListener(this);


        fm = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        // remove dialog title
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // remove dialog background
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return v;
    }

    @Override
    public void onClick(View view) {
        getDialog().dismiss();
    }
}
