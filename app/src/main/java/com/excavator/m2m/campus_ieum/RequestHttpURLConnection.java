package com.excavator.m2m.campus_ieum;

import android.content.ContentValues;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Beginner on 2018. 1. 21..
 */

public class RequestHttpURLConnection {

    public String request(String _url, ContentValues _params) {
        // HttpURLConnection 참조
        HttpURLConnection urlConn = null;
        // URL 파라미터
        StringBuffer sbParams = new StringBuffer();

        /**
         *  1. StringBuffer 파라미터 연결
         **/

        // 파라미터가 없으면 비운다.
        if (_params == null)
            sbParams.append("");
            // 파라미터가 있으면 채운다.
        else {
            // 파라미터가 2개 이상이면 '/'로 구분하기 위한 flag 변수
            boolean isSlash = false;
            // 파라미터 키 / 값
            String paramStr;

            for (String param: _params.keySet()) {
            // for (Map.Entry<String, Object> parameter : _params.valueSet()) {
                // key = parameter.getKey();
                // value = parameter.getValue().toString();

                // 파라미터를 String 값으로 저장해서 paramStr에 저장
                paramStr = param;

                // URL 사이에 파라미터 구분 문자 추가
                if (isSlash)
                    sbParams.append("/");
                // sbParams.append(key).append("/").append(value);

                sbParams.append(paramStr);

                Log.i("DEBUG", "params string: " + sbParams.toString());

                if (!isSlash)
                    if (_params.size() >= 2)
                        isSlash = true;

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
            urlConn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset
            urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");

            // parameter 전달 및 데이터 파싱
            String strParams = sbParams.toString(); // sbParams에 저장한 파라미터를 문자열로 저장
            Log.i("DEBUG", "sbParams: " + strParams);
            OutputStream os = urlConn.getOutputStream();
            os.write(strParams.getBytes("UTF-8")); // 출력 스트림에 출력
            os.flush(); // 출력 스트림을 비우고 버퍼링 된 모든 출력 바이트 강제 실행
            os.close(); // 출력 스트림을 닫고 모든 시스템 자원 해제

            // 연결 요청 확인
            // 실패 시 null을 리턴하고 메소드 종료
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
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
