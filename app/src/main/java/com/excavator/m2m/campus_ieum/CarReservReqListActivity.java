package com.excavator.m2m.campus_ieum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import java.util.ArrayList;

/**
 * Created by Beginner on 2018. 2. 1..
 */

public class CarReservReqListActivity extends Fragment {

    View v;
    FragmentManager manager;

    private MapView mapView = null;

    private static RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;

    private static ArrayList<ReqItem> carReqList;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // inflate 메소드 => xml 데이터를 실제 View 객체로
        v = inflater.inflate(R.layout.content_car_tab_main, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("차량호출");

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) v.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        return v;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends android.support.v4.app.Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.content_car_tab_list, container, false);
            // TextView textView = (TextView) rootView.findViewById(R.id.carFragmentLabel);
            // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
            toolbarTitle.setText("차량호출 - 예약현황");

            carReqList = new ArrayList<> ();
            carReqList.add(new ReqItem("홍길동", "11.12/00:00", "즉시", false));
            carReqList.add(new ReqItem("김영수", "11.12/00:00", "즉시", true));
            carReqList.add(new ReqItem("김영희", "11.12/00:00", "예약", false));
            carReqList.add(new ReqItem("홍다나", "11.12/00:00", "즉시", true));
            carReqList.add(new ReqItem("홍길동", "11.12/00:00", "예약", false));
            carReqList.add(new ReqItem("김영수", "11.12/00:00", "예약", true));
            carReqList.add(new ReqItem("김영희", "11.12/00:00", "즉시", false));
            carReqList.add(new ReqItem("홍다나", "11.12/00:00", "예약", true));

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.carListRecycler);
            mRecyclerView.setHasFixedSize(true);

            mAdapter = new RecyclerCardViewTab(carReqList);
            mRecyclerView.setAdapter(mAdapter);


            return rootView;
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    public static class ReqItem {
        String name;
        String date;
        String type;
        boolean check;

        public ReqItem(String name, String date, String type, boolean check) {
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
