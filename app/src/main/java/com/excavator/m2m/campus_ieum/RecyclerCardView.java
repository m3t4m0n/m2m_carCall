package com.excavator.m2m.campus_ieum;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Beginner on 2018. 1. 28..
 */

public class RecyclerCardView extends RecyclerView.Adapter <RecyclerCardView.ViewHolder> {

    private ArrayList<CarReservListActivity.ReservItem> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mName;
        public TextView mDate;
        public TextView mType;
        public ImageView mCheck;

        public ViewHolder(View v) {
            super(v);

            mName = (TextView) v.findViewById(R.id.carReservName);
            mDate = (TextView) v.findViewById(R.id.carReservDate);
            mType = (TextView) v.findViewById(R.id.carReservType);
            mCheck = (ImageView) v.findViewById(R.id.carReservCheck);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Clicked card position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();

            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            // activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_home, CarReservInfoActivity.newInstance("이름: " + mDataset.get(getLayoutPosition()).getName(), "세부위치: 경천관", "메모: 동승자 없음", "날짜/시간: " + mDataset.get(getLayoutPosition()).getDate(), "전화번호: 010-2339-6754")).addToBackStack(null).commit();

            // CustomDialog dialog = new CustomDialog(getActivity(), "차량 이용시간이 아닙니다.", "(이용가능시간: 00:00 ~ 09:00)");
            // dialog.show();

            Bundle args = new Bundle();
            args.putString("mName", mDataset.get(getLayoutPosition()).getName());
            args.putString("mLocation", "경천관 3층 앞");
            args.putString("mComment", "동승자 있음");
            args.putString("mDate", mDataset.get(getLayoutPosition()).getDate());
            args.putString("mPhone", "010-1234-5678");

            FragmentManager fm = activity.getSupportFragmentManager();

            CustomDialogCarInfo dialog = new CustomDialogCarInfo();
            dialog.setArguments(args);
            dialog.show(fm,"custom_dialog_car_info");
        }

    }

    public RecyclerCardView(ArrayList<CarReservListActivity.ReservItem> carReservList) {
        mDataset = carReservList;
    }

    @Override
    public RecyclerCardView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_car_list_cardview, parent, false);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mName.setText(mDataset.get(position).getName());
        holder.mDate.setText(mDataset.get(position).getDate());
        holder.mType.setText(mDataset.get(position).getType());

        if(!mDataset.get(position).getCheck()) {
            holder.mCheck.setImageResource(R.mipmap.ic_car_uncheck);
        }

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
