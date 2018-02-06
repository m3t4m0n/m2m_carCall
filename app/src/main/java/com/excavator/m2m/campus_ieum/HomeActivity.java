package com.excavator.m2m.campus_ieum;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Beginner on 2018. 1. 18..
 */

public class HomeActivity extends Fragment {
    View view;
    FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // inflate 메소드 => xml 데이터를 실제 View 객체로
        view = inflater.inflate(R.layout.content_home, container, false);

        manager = getFragmentManager();

        SharedPreferences sp = getActivity().getSharedPreferences("user_info", Activity.MODE_PRIVATE);
        String role = sp.getString("type", "");
        role.trim();

        String strRole = "차량 도우미";
        /*
        switch(Integer.parseInt(role)) {
            case 1: strRole = "도우미 학생"; break;
            case 2: strRole = "차량 도우미"; break;
            default: strRole = "학생"; break;
        }
        */

        TextView profileRole = (TextView) view.findViewById(R.id.profileRole);
        profileRole.setText(strRole);

        Button btnBell = (Button) view.findViewById(R.id.buttonBell);
        btnBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction().replace(R.id.content_home, new BellActivity()).commit();

            }
        });

        Button btnCar = (Button) view.findViewById(R.id.buttonCar);
        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction().replace(R.id.content_home, new CarActivity()).commit();
            }
        });


        return view;
    }



}
