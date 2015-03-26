package com.olme.application;

import android.app.Application;

/**
 * Created by Bingo on 2014/8/9.
 * 全局变量，用于记住登陆状态信息
 */
public class CustomApplication extends Application {
    private static final String VALUE = "Logout";

    private String value;
    private Integer userId;
    private String userName;
    private String userPhoto;
    private String userSex;

    @Override
    public void onCreate() {
        super.onCreate();
        setValue(VALUE); // 初始化全局变量
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}

