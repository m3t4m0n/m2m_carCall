package com.excavator.m2m.campus_ieum;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Beginner on 2018. 1. 26..
 */

public class CustomDialogFragment extends DialogFragment {

    private Fragment fm;

    String mTxt;
    String mTxt2;

    public CustomDialogFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mTxt = getArguments().getString("mTxt");
            mTxt2 = getArguments().getString("mTxt2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.custom_alert, container);

        TextView mTxtView = v.findViewById(R.id.alertTxt);
        TextView mTxtView2 = v.findViewById(R.id.alertTxt2);

        mTxtView.setText(mTxt);
        mTxtView2.setText(mTxt2);

        fm = getActivity().getSupportFragmentManager().findFragmentByTag("tag");


        // remove dialog title
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // remove dialog background
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return v;
    }

}
