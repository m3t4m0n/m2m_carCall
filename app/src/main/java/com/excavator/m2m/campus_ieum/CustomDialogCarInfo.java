package com.excavator.m2m.campus_ieum;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Beginner on 2018. 1. 29..
 */

public class CustomDialogCarInfo extends DialogFragment {
    private Fragment fm;
    private Button btnCarAllow, btnCarRefuse;

    private String mName;
    private String mLocation;
    private String mComment;
    private String mDate;
    private String mPhone;

    public CustomDialogCarInfo() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mName = getArguments().getString("mName");
            mLocation = getArguments().getString("mLocation");
            mComment = getArguments().getString("mComment");
            mDate = getArguments().getString("mDate");
            mPhone = getArguments().getString("mPhone");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_car_alert, container);

        TextView mNameView = (TextView) v.findViewById(R.id.alertReservName);
        TextView mLocationView = (TextView) v.findViewById(R.id.alertReservLocation);
        TextView mCommentView = (TextView) v.findViewById(R.id.alertReservComment);
        TextView mDateView = (TextView) v.findViewById(R.id.alertReservDate);
        TextView mPhoneView = (TextView) v.findViewById(R.id.alertReservPhone);

        mNameView.setText(mName);
        mLocationView.setText(mLocation);
        mCommentView.setText(mComment);
        mDateView.setText(mDate);
        mPhoneView.setText(mPhone);

        fm = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        // remove dialog title
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // remove dialog background
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        btnCarAllow = (Button) v.findViewById(R.id.alertReservAllow);
        btnCarAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "예약 수락", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

        btnCarRefuse = (Button) v.findViewById(R.id.alertReservDeny);
        btnCarRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "예약 거절", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

        return v;
    }

}

