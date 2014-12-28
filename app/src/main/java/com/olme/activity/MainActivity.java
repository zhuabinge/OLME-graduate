package com.olme.activity;
import android.app.Activity;
import android.content.Intent;

import com.olme.application.ExitApplication;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {


    @AfterViews
    void init(){
        ExitApplication.getInstance().addActivity(this);
        try {

            Intent intent = new Intent(this, LoginActivity_.class);
            Thread.sleep(10000);
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
