package com.excavator.m2m.campus_ieum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Beginner on 2018. 1. 18..
 */

public class CarActivity extends Fragment {
    View v;
    FragmentManager manager;
    boolean flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // inflate 메소드 => xml 데이터를 실제 View 객체로
        v = inflater.inflate(R.layout.content_car, container, false);
        manager = getFragmentManager();

        String url = "http://m2m.pilot0613.com/car_call/status";

        /*
        ContentValues paramValues = new ContentValues();
        paramValues.put("car_call", "");
        paramValues.put("status", "");
        */

        ImageView carStatusImg = v.findViewById(R.id.carStatus);
        TextView carStatusTxt = v.findViewById(R.id.text_carStatus);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("차량호출");

        flag = false;

        Button btnCarCall = (Button) v.findViewById(R.id.btnCarCall);
        btnCarCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CustomDialog dialog = new CustomDialog(getActivity(), "차량 이용시간이 아닙니다.", "(이용가능시간: 00:00 ~ 09:00)");
                // dialog.show();

                if(flag) {
                    Bundle args = new Bundle();
                    args.putString("mTxt", "차량 이용시간이 아닙니다.");
                    args.putString("mTxt2", "(이용가능시간: 00:00 ~ 00:00)");


                    FragmentManager fm = getActivity().getSupportFragmentManager();

                    CustomDialogFragment dialog = new CustomDialogFragment();
                    dialog.setArguments(args);
                    dialog.show(fm, "custom_dialog_fragment");
                }
                else {
                    Bundle args = new Bundle();

                    FragmentManager fm = getActivity().getSupportFragmentManager();

                    /*
                    CustomDialogReservComplete dialog = new CustomDialogReservComplete();

                    dialog.setArguments(args);
                    dialog.show(fm, "custom_dialog_reserve_complete");
                    */

                    manager.beginTransaction().replace(R.id.content_home, new CarReservReqListActivity()).commit();
                }
            }
        });

        Button btnCarReserv = (Button) v.findViewById(R.id.btnCarReserv);
        btnCarReserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction().replace(R.id.content_home, new CarReservListActivity()).commit();
            }
        });

        // AsyncTask를 통해 HttpURLConnection 실행
        NetworkTask networkTask = new NetworkTask(url, null);


        String carStatus = null;
        try {
            carStatus = networkTask.execute().get();
            Log.i("get() Test", carStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(carStatus.equals("disavailable")) {
            carStatusImg.setImageResource(R.mipmap.ic_car_disavailable);
            carStatusTxt.setText("운전중");
        }


        return v;

    }

}
