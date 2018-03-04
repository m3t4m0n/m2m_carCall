package com.excavator.m2m.campus_ieum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Beginner on 2018. 2. 9..
 */

public class CarReservLocationMapActivity extends Fragment implements OnMapReadyCallback {
    View v;
    private MapView mapView;

    public CarReservLocationMapActivity() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // inflate 메소드 => xml 데이터를 실제 View 객체로
        v = inflater.inflate(R.layout.content_car_reserv_location_map, container, false);

        mapView = (MapView) v.findViewById(R.id.carReservMap);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        return v;
    }


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(this.getActivity());

        LatLng KNU = new LatLng(37.27, 127.13);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(KNU);
        markerOptions.title("강남대");
        markerOptions.snippet("경기도 용인시 소재의 복지 특성화 대학교");

        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(KNU));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));

    }
}
