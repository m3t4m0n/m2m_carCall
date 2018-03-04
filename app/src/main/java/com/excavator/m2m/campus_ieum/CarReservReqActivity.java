package com.excavator.m2m.campus_ieum;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by Beginner on 2018. 2. 12..
 */

public class CarReservReqActivity extends Fragment {
    View v;
    FragmentManager manager;

    private TextView locationSelect;
    private TextView dateSelect;
    private TextView timeSelect;
    private TextView editMemo;
    private CheckBox checkWheel;
    private CheckBox checkFriend;

    private String location;
    private String memo;
    private boolean wheel, friend;
    // private String[] date;
    // private String[] time;
    private String date;
    private String time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            location = getArguments().getString("location");
            Log.i("Location Select", location);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // inflate 메소드 => xml 데이터를 실제 View 객체로
        v = inflater.inflate(R.layout.content_car_reserv_req, container, false);

        final Calendar cal = Calendar.getInstance();

        locationSelect = (TextView) v.findViewById(R.id.locationTxt);
        locationSelect.setText(location);

        dateSelect = (TextView) v.findViewById(R.id.dateTxt);
        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        /*
                        date[0] = Integer.toString(year);
                        date[1] = Integer.toString(month);
                        date[2] = Integer.toString(dayOfMonth);
                        */

                        String msg = String.format("%d년 %d월 %d일", year, month+1, dayOfMonth);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

                        date = msg;
                        dateSelect.setText(date);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });

        timeSelect = (TextView) v.findViewById(R.id.timeTxt);
        timeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        /*
                        time[0] = Integer.toString(hourOfDay);
                        time[1] = Integer.toString(minute);
                        */

                        String msg = String.format("%d시 %d분", hourOfDay, minute);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

                        time = msg;
                        timeSelect.setText(time);
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);

                timePickerDialog.show();
            }
        });

        editMemo = (TextView) v.findViewById(R.id.editMemo);
        editMemo.setText("");

        checkWheel = (CheckBox) v.findViewById(R.id.checkWheel);
        checkFriend = (CheckBox) v.findViewById(R.id.checkFriend);

        Button btnReservSubmit = (Button) v.findViewById(R.id.btnCarReservSubmit);
        btnReservSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memo = editMemo.getText().toString();
                wheel = checkWheel.isChecked();
                friend = checkFriend.isChecked();

                if(date != null) {
                    Toast.makeText(getContext(), "날짜를 선택하세요.", Toast.LENGTH_SHORT).show();
                }
                else if(time != null) {
                    Toast.makeText(getContext(), "시간을 선택하세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    manager = getActivity().getSupportFragmentManager();
                    if (sendRequest(location, date, time, memo, wheel, friend).equals("true")) {
                        CustomDialogReservComplete dialog = new CustomDialogReservComplete();

                        dialog.show(manager, "custom_dialog_reserve_complete");

                        manager.beginTransaction().replace(R.id.content_home, new CarActivity()).commit();
                    } else {
                        manager.beginTransaction().replace(R.id.content_home, new CarReservReqActivity()).commit();
                    }
                }

            }
        });

        return v;
    }


    public String sendRequest(String location, String date, String time, String memo, boolean wheel, boolean friend) {
        // 혹시 모르니까 일단 만들어 두는
        this.location = location;
        this.date = date;
        this.time = time;
        this.memo = memo;
        this.wheel = wheel;
        this.friend = friend;

        UserAuthInfo userAuthInfo = UserAuthInfo.getInstance();

        ContentValues paramValues = new ContentValues();
        paramValues.put("location", location);
        paramValues.put("date", date);
        paramValues.put("time", time);
        paramValues.put("memo", memo);
        paramValues.put("wheel", wheel);
        paramValues.put("friend", friend);
        paramValues.put("name", userAuthInfo.id);

        String url = "http://temp_m2m.pilot0613.com/car_call/carReservRequest";
        NetworkTask networkTask = new NetworkTask(url, paramValues);

        String flag = null;
        try {
            flag = networkTask.execute().get();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}