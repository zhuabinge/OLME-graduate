package com.olme.application;

import android.app.Application;

/**
 * Created by Bingo on 2014/8/9.
 * 全局变量，用于记住登陆状态信息
 */
public class CustomApplication extends Application {
    private static final String VALUE = "Logout";

    private String value;

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
}

