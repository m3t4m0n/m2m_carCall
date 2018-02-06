package com.excavator.m2m.campus_ieum;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Beginner on 2018. 1. 29..
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TAG", "Refreshed token: " + token);

        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {

        /*
        ContentValues paramValues = new ContentValues();
        paramValues.put("car_call", "");
        paramValues.put("status", "");
        */

        /*
        ContentValues paramValues = new ContentValues();

        paramValues.put("token", token);


        String url = "http://m2m.pilot0613.com/fcm/register";
        NetworkTask networkTask = new NetworkTask(url, paramValues);

        try {
            networkTask.execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        */


        /*
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        Request request = new Request.Builder()
                .url("http://m2m.pilot0613.com/fcm/register")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch(IOException e) {
            e.printStackTrace();
        }
        */


    }

}
