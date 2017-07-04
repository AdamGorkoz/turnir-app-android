package com.models;

/**
 * Created by turka on 7/4/2017.
 */

public class LoginResultModel {
    private String access_token;
    private int role;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
