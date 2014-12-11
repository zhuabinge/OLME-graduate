package com.olme.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @AfterViews
    void init(){}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
