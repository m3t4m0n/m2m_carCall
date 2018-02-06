package com.excavator.m2m.campus_ieum;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Beginner on 2018. 1. 21..
 */

public class RequestHttpURLConnection {

    public String request(String _url, ContentValues _params) {
        // HttpURLConnection 참조
        HttpURLConnection urlConn = null;
        // URL 파라미터
        StringBuffer sbParams = new StringBuffer();
        // JSON Object
        JSONObject jObj = new JSONObject();

        /**
         *  1. StringBuffer 파라미터 연결
         **/

        // 파라미터가 없으면 비운다.
        if (_params == null)
            sbParams.append("");
        // 파라미터가 있으면 채운다.
        else {
            // 파라미터가 2개 이상이면 '/'로 구분하기 위한 flag 변수
            // boolean isAnd = false;
            // 파라미터 키 / 값
            String key;
            String value;

            // for (String param: _params.keySet()) {
            for (Map.Entry<String, Object> parameter : _params.valueSet()) {
                key = parameter.getKey();
                value = parameter.getValue().toString();

                // URL 사이에 파라미터 구분 문자 추가
                /*
                if (isAnd)
                sbParams.append("&");
                */

                // sbParams.append(key).append("/").append(value);

                try {
                    jObj.put(key, value);
                } catch(JSONException je) {
                    je.printStackTrace();
                }

                /*
                if (!isAnd)
                    if (_params.size() >= 2)
                isAnd = true;
                */
            }

        }

        /**
         *  2. HttpURLConnection을 통해 web 데이터를 가져온다.
         **/

        try {
            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();

            Log.i("DEBUG", url.toString());

            // urlConn 설정
            urlConn.setRequestMethod("POST"); // 요청에 대한 메소드
            // urlConn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset
            // urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");
            urlConn.setRequestProperty("Cache-Control", "no-cache");
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setRequestProperty("Accept", "application/json");


            // parameter 전달 및 데이터 파싱
            // String strParams = sbParams.toString(); // sbParams에 저장한 파라미터를 문자열로 저장
            String strParams = jObj.toString();
            Log.i("DEBUG", "sbParams: " + strParams);
            urlConn.setDoOutput(true);
            // urlConn.setDoInput(true);

            OutputStream os = urlConn.getOutputStream();
            // DataOutputStream os = new DataOutputStream(urlConn.getOutputStream());
            // os.write(strParams.getBytes("UTF-8")); // 출력 스트림에 출력
            os.write(strParams.getBytes("UTF-8"));

            os.flush(); // 출력 스트림을 비우고 버퍼링 된 모든 출력 바이트 강제 실행
            os.close(); // 출력 스트림을 닫고 모든 시스템 자원 해제

            // 연결 요청 확인
            // 실패 시 null을 리턴하고 메소드 종료
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.i("NETWORK ERROR", String.valueOf(urlConn.getResponseCode()));
                return null;
            }

            // 파싱한 결과물 반환
            // 요청한 URL의 결과를 BufferedReader로 받는다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));

            // 결과의 라인과 그 합
            String line, page = "";

            // 라인을 받아와 합친다.
            while ((line = reader.readLine()) != null)
                page += line;

            return page;

        } catch (MalformedURLException e) { // for URL
            e.printStackTrace();
        } catch (IOException e) { // for openConnection
            e.printStackTrace();
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
        }

        return null;
    }
}
