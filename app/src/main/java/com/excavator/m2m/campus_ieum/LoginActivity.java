package com.excavator.m2m.campus_ieum;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Beginner on 2018. 1. 31..
 */

public class LoginActivity extends AppCompatActivity {

    // 뒤로 가기 버튼 눌린 시간 측정
    private long lastTimeBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final SharedPreferences user_info = getSharedPreferences("user_info", 0);
        String id = user_info.getString("id", "");
        String pw = user_info.getString("pass", "");

        if(!id.equals("") && !pw.equals("")) {
            String url = "http://temp_m2m.pilot0613.com/user/getInfo";

            ContentValues loginParam = new ContentValues();
            loginParam.put("id", id);

            NetworkTask networkTask = new NetworkTask(url, loginParam);
            String info = null;
            try {
                info = networkTask.execute().get();
                Log.i("get_role: ", info);
            } catch(Exception e) {
                e.printStackTrace();
            }

            if(info != null) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        }

        final EditText idEdit = (EditText)findViewById(R.id.idEdit_Login);
        final EditText passEdit = (EditText)findViewById(R.id.passEdit_Login);

        Switch sw = (Switch)findViewById(R.id.autoLogin_Login);
        if(!user_info.getString("id", "").equals("")) {
            /*
            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... strings) {
                    System.out.println("url : " + strings[0]);
                    URL Url = null;
                    String line = null;
                    try {
                        Url = new URL(strings[0]);
                        HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                        conn.setRequestMethod("GET");
                        InputStream is = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        line = reader.readLine();
                        System.out.println(line);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return line;
                }

                @Override
                protected void onPostExecute(String s) {
                    try {
                        JSONObject jObj = new JSONObject(s);
                        String message = jObj.getString("msg");
                        System.out.println(message);
                        if (message.equals("ok")) {
                            Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.execute("http:/localhost:8080/myopenapi.jsp?method=login&userId=" + loginInformation.getString("id", "") + "&userPass=" + loginInformation.getString("pass", ""));
            */

            // sw.setChecked(true);
        }

        TextView findPass = (TextView) findViewById(R.id.findPassTV_Login);
        findPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResetPassFirstActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        Button signupBtn = (Button)findViewById(R.id.signupBtn_Login);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupSelectActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //자동로그인 체크 시와 해제시의 기능 구분
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    SharedPreferences.Editor editor = user_info.edit();
                    editor.putString("id", idEdit.getText().toString());
                    editor.putString("pass", passEdit.getText().toString());
                    editor.commit();
                }else{
                    SharedPreferences.Editor editor = user_info.edit();
                    editor.putString("id", "");
                    editor.putString("pass", "");
                    editor.commit();
                }
            }
        });

        Button loginBtn = (Button)findViewById(R.id.loginBtn_Login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userId = (EditText) findViewById(R.id.idEdit_Login);
                EditText userPass = (EditText) findViewById(R.id.passEdit_Login);

                if(!userId.getText().toString().equals("") && !userPass.getText().toString().equals("")) {
                    String url = "http://temp_m2m.pilot0613.com/user/signin";

                    ContentValues paramValues = new ContentValues();
                    paramValues.put("name", userId.getText().toString());
                    paramValues.put("password", userPass.getText().toString());

                    NetworkTask signInTask = new NetworkTask(url, paramValues);

                    String result = null;
                    try {
                        result = signInTask.execute().get();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                    Log.i("sign result: ", result);

                    /*
                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... strings) {
                            System.out.println("url : " + strings[0]);
                            URL Url = null;
                            String line = null;
                            try {
                                Url = new URL(strings[0]);
                                HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                                conn.setRequestMethod("GET");
                                InputStream is = conn.getInputStream();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                                line = reader.readLine();
                                System.out.println(line);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return line;
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject jObj = new JSONObject(s);
                                String message = jObj.getString("msg");
                                System.out.println(message);
                                if (message.equals("ok")) {
                                    Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    overridePendingTransition(0, 0);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.execute("http:/localhost:8080/myopenapi.jsp?method=login&userId=" + userId.getText().toString() + "&userPass=" + userPass.getText().toString());
                    */

                    if(result.equals("true")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                    else {
                        userId.setText("");
                        userPass.setText("");
                        Toast.makeText(getApplicationContext(), "잘못된 정보입니다. 다시 입력하세요!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    if(userId.getText().toString().equals("") && userPass.getText().toString().equals(""))
                        Toast.makeText(getApplicationContext(), "아이디, 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    else if(userId.getText().toString().equals(""))
                        Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    else if(userPass.getText().toString().equals(""))
                        Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //뒤로가기를 두번 누르면 꺼지는 설정
    public void onBackPressed(){
        if (System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish();
            return;
        }
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}