package com.excavator.m2m.campus_ieum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Beginner on 2018. 1. 28..
 */

public class CarReservInfoTemp extends Fragment {
    View v;
    FragmentManager manager;

    String mName;
    String mLocation;
    String mComment;
    String mDate;
    String mPhone;

    public static CarReservInfoTemp newInstance(String mName, String mLocation, String mComment, String mDate, String mPhone) {
        CarReservInfoTemp fragment = new CarReservInfoTemp();
        Bundle args = new Bundle();

        args.putString("mName", mName);
        args.putString("mLocation", mLocation);
        args.putString("mComment", mComment);
        args.putString("mDate", mDate);
        args.putString("mPhone", mPhone);

        fragment.setArguments(args);
        return fragment;

    }

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // inflate 메소드 => xml 데이터를 실제 View 객체로
        v = inflater.inflate(R.layout.content_car_info, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("차량호출 - 이용정보");

        TextView mReservName = (TextView) v.findViewById(R.id.carInfoName);
        TextView mReservLocation = (TextView) v.findViewById(R.id.carInfoLocation);
        TextView mReservComment = (TextView) v.findViewById(R.id.carInfoComment);
        TextView mReservDate = (TextView) v.findViewById(R.id.carInfoDate);
        TextView mReservPhone = (TextView) v.findViewById(R.id.carInfoContact);

        mReservName.setText(mName);
        mReservLocation.setText(mLocation);
        mReservComment.setText(mComment);
        mReservDate.setText(mDate);
        mReservPhone.setText(mPhone);

        return v;
    }



}
