package com.excavator.m2m.campus_ieum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Beginner on 2018. 1. 18..
 */

public class CarReservListActivity extends Fragment {
    View v;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private static ArrayList<ReservItem> carReservList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // inflate 메소드 => xml 데이터를 실제 View 객체로
        v = inflater.inflate(R.layout.content_car_list, container, false);

        String url = "http://m2m.pilot0613.com/car_call/status";

        /*
        ContentValues paramValues = new ContentValues();
        paramValues.put("car_call", "");
        paramValues.put("status", "");
        */

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("차량호출 - 이용리스트");

        /*
        Button btnCarCall = (Button) v.findViewById(R.id.btnCarCall);
        btnCarCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CustomDialog dialog = new CustomDialog(getActivity(), "차량 이용시간이 아닙니다.", "(이용가능시간: 00:00 ~ 09:00)");
                // dialog.show();
                Bundle args = new Bundle();

                FragmentManager fm = getActivity().getSupportFragmentManager();

                CustomDialogFragment dialog = new CustomDialogFragment();
                dialog.setArguments(args);
                dialog.show(fm,"custom_dialog_fragment");

            }
        });
        */

        // AsyncTask를 통해 HttpURLConnection 실행
        /*
        NetworkTask networkTask = new NetworkTask(url, null);


        String carStatus = null;
        try {
            carStatus = networkTask.execute().get();
            Log.i("get() Test", carStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(carStatus.equals("disavailable")) {
            // carStatusImg.setImageResource(R.mipmap.ic_car_disavailable);
            // carStatusTxt.setText("운전중");
        }
        */


        carReservList = new ArrayList<> ();
        carReservList.add(new ReservItem("홍길동", "11.12/00:00", "즉시", false));
        carReservList.add(new ReservItem("김영수", "11.12/00:00", "즉시", true));
        carReservList.add(new ReservItem("김영희", "11.12/00:00", "예약", false));
        carReservList.add(new ReservItem("홍다나", "11.12/00:00", "즉시", true));
        carReservList.add(new ReservItem("홍길동", "11.12/00:00", "예약", false));
        carReservList.add(new ReservItem("김영수", "11.12/00:00", "예약", true));
        carReservList.add(new ReservItem("김영희", "11.12/00:00", "즉시", false));
        carReservList.add(new ReservItem("홍다나", "11.12/00:00", "예약", true));

        mRecyclerView = (RecyclerView) v.findViewById(R.id.carListRecycler);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerCardView(carReservList);
        mRecyclerView.setAdapter(mAdapter);

        return v;

    }

    public class ReservItem {
        String name;
        String date;
        String type;
        boolean check;

        public ReservItem(String name, String date, String type, boolean check) {
            this.name = name;
            this.date = date;
            this.type = type;
            this.check = check;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getType() {
            return type;
        }

        public boolean getCheck() {
            return check;
        }


    }

}
