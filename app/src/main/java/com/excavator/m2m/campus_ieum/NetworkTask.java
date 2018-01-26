package com.excavator.m2m.campus_ieum;

/**
 * Created by Beginner on 2018. 1. 22..
 */

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

/**
 *  1. HttpURLConnection 통신을 위한 비동기 처리 AsyncTask 구현
 **/
public class NetworkTask extends AsyncTask<Void, Void, String> {

    private String url;
    private ContentValues values;

    public NetworkTask(String url, ContentValues values) {
        this.url = url;
        this.values = values;

    }

    @Override
    protected String doInBackground(Void... params) {
        String result; // 요청한 결과물 저장
        RequestHttpURLConnection reqHttpUrlConn = new RequestHttpURLConnection();

        result = reqHttpUrlConn.request(url, values); // 해당 URL로부터 결과물을 받아옴

        return result;
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);

        // doInBackground 로부터 반환된 값이 onPostExecute 매개변수로 넘어오므로 str 출력
        Log.i("DEBUG", str);
    }


}