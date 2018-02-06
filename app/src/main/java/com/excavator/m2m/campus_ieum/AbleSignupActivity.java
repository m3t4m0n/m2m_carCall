package com.excavator.m2m.campus_ieum;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by aks56 on 2017-12-31.
 */

public class AbleSignupActivity extends AppCompatActivity {
    boolean manFlag = false, womanFlag = false;
    int helpKind = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signup_able);

        final String[] strings = {"도우미 유형 선택", "도우미 학생", "차량 도우미"};


        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, strings);
        Spinner spinner = (Spinner)findViewById(R.id.helpKind_AbleSignup);
        spinner.setAdapter(spinnerAdapter);

        /*
        Button backBtn = (Button) findViewById(R.id.backBtn_AbleSignup);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0, 0);
            }
        });
        */

        String phoneNum = null;
        TelephonyManager telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }else{
            phoneNum = telephonyManager.getLine1Number();
        }

        final EditText phoneNumEdit = (EditText)findViewById(R.id.phoneNumEdit_AbleSignup);
        phoneNumEdit.setText(phoneNum);

        RadioGroup genderRG = (RadioGroup)findViewById(R.id.genderRG_AbleSignup);
        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.manRB_AbleSignup){
                    manFlag = true;
                    womanFlag = false;
                }else if(i == R.id.womanRB_AbleSignup){
                    womanFlag = true;
                    manFlag = false;
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                helpKind = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button signupBtn = (Button)findViewById(R.id.signupBtn_AbleSignup);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userName = (EditText)findViewById(R.id.userNameEdit_AbleSignup);
                EditText userPass = (EditText)findViewById(R.id.passEdit_AbleSignup);
                EditText userRepass = (EditText)findViewById(R.id.repassEdit_AbleSignup);

                if(userName.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else if(phoneNumEdit.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "휴대폰 인증을 진행해주세요.", Toast.LENGTH_SHORT).show();
                }else if(userPass.getText().toString().equals("") || userRepass.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                else if(userPass.getText().length() < 4)
                    Toast.makeText(getApplicationContext(), "비밀번호를 4자리 이상으로 입력해주세요.", Toast.LENGTH_SHORT).show();
                else if(!userPass.getText().toString().equals(userRepass.getText().toString()))
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                else if(!manFlag && !womanFlag)
                    Toast.makeText(getApplicationContext(), "성별을 선택해주세요.", Toast.LENGTH_SHORT).show();
                else if(helpKind == 0)
                    Toast.makeText(getApplicationContext(), "유형을 선택해주세요.", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }

                ContentValues paramValues = new ContentValues();

                paramValues.put("name", userName.getText().toString());
                paramValues.put("phone", phoneNumEdit.getText().toString());
                paramValues.put("password", userPass.getText().toString());
                paramValues.put("gender", manFlag ? "man" : "woman");
                paramValues.put("type", helpKind);

                MyFirebaseInstanceIDService mfbidservice = new MyFirebaseInstanceIDService();
                mfbidservice.onTokenRefresh();
                FirebaseMessaging.getInstance().subscribeToTopic("car_call");
                paramValues.put("token", FirebaseInstanceId.getInstance().getToken());

                String url = "http://temp_m2m.pilot0613.com/user/signup";
                NetworkTask signUpTask = new NetworkTask(url, paramValues);

                String flag = null;
                try {
                    flag = signUpTask.execute().get();
                } catch(Exception e) {
                    e.printStackTrace();
                }

                Log.i("DEBUG", flag);

                if(flag.equals("complete")) {
                    SharedPreferences sp = getSharedPreferences("user_info", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();

                    editor.putString("name", userName.getText().toString());
                    editor.putString("phone", phoneNumEdit.getText().toString());
                    editor.putString("password", userPass.getText().toString());
                    editor.putString("gender", manFlag ? "man" : "woman");
                    editor.putString("type", Integer.toString(helpKind));

                    editor.commit();
                }

            }
        });
    }

    public void onBackPressed(){
        finish();
        overridePendingTransition(0, 0);
    }
}
