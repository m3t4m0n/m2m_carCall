package com.excavator.m2m.campus_ieum;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Beginner on 2018. 1. 26..
 */

public class CustomDialog extends Dialog implements View.OnClickListener {

    private static final int alert_layout = R.layout.alert;

    private Context context;

    private TextView mAlertTxt;
    private TextView mAlertTxt2;

    private Button mAlertBtn;

    private String mTxt;
    private String mTxt2;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog(Context context, String mTxt, String mTxt2) {
        super(context);
        this.context = context;
        this.mTxt = mTxt;
        this.mTxt2 = mTxt2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(alert_layout);

        mAlertTxt = (TextView) findViewById(R.id.alertTxt);
        mAlertTxt2 = (TextView) findViewById(R.id.alertTxt2);

        mAlertBtn = (Button) findViewById(R.id.alertBtn);
        mAlertBtn.setOnClickListener(this);

        if((mTxt != null) && (mTxt2 != null)) {
            mAlertTxt.setText(mTxt);
            mAlertTxt2.setText(mTxt2);
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.alertBtn:
                Toast.makeText(context, "확인 클릭", Toast.LENGTH_SHORT).show();
        }
    }

}
