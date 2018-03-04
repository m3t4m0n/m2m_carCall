package com.excavator.m2m.campus_ieum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Beginner on 2018. 2. 9..
 */

public class CarReservLocationActivity extends Fragment {
    View v;
    FragmentManager manager;

    private Spinner locationSpinner;
    private String location;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // inflate 메소드 => xml 데이터를 실제 View 객체로
        v = inflater.inflate(R.layout.content_car_reserv_location, container, false);

        manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.carReservMapFragment, new CarReservLocationMapActivity()).commit();

        locationSpinner = (Spinner) v.findViewById(R.id.locationSpinner);

        final String[] locationList = { "샬롬관", "인사관", "경천관", "이공관", "예술관", "목양관", "우원관", "본관", "천은관", "승리관" };

        /*
        String[] locationList = new String[10];
        locationList[0] = "샬롬관";
        locationList[1] = "인사관";
        locationList[2] = "경천관";
        locationList[3] = "이공관";
        locationList[4] = "예술관";
        locationList[5] = "목양관";
        locationList[6] = "우원관";
        locationList[7] = "본관";
        locationList[8] = "천은관";
        locationList[9] = "승리관";
        */

        /*
        locationList.add("경천관");
        locationList.add("이공관");
        locationList.add("예술관");
        locationList.add("목양관");
        locationList.add("우원관");
        locationList.add("본관");
        locationList.add("천은관");
        locationList.add("승리관");
        */


        ArrayAdapter spinnerAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, locationList);
        locationSpinner.setAdapter(spinnerAdapter);

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location =  locationSpinner.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), location, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        Button btnLocationSubmit = (Button) v.findViewById(R.id.carSelectLocation);
        btnLocationSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("location", location);

                CarReservReqActivity carReservReqActivity = new CarReservReqActivity();
                carReservReqActivity.setArguments(args);

                manager.beginTransaction().replace(R.id.content_home, carReservReqActivity).commit();
            }
        });

        return v;
    }

}
