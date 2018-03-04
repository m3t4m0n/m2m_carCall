package com.excavator.m2m.campus_ieum;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Beginner on 2018. 2. 8..
 */

public class UserAuthInfo {
    public String id;
    public String pw;
    public String phone;
    public String gender;
    public String role_type;

    private UserAuthInfo() { }

    private static class InstanceHolder {
        private static final UserAuthInfo INSTANCE = new UserAuthInfo();
    }

    public static UserAuthInfo getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void receiveObject(JSONObject data) {
        try {
            id = data.getString("name");
            phone = data.getString("phone");
            gender = data.getString("gender");
            role_type = data.getString("role_type");

        } catch(JSONException je) {
            je.printStackTrace();
        }
    }
}
