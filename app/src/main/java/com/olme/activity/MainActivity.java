package com.olme.activity;
import android.app.Activity;
import android.content.Intent;

import com.olme.R;
import com.olme.application.ExitApplication;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.Timer;
import java.util.TimerTask;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {


    @AfterViews
    void init(){
        ExitApplication.getInstance().addActivity(this);
        final Intent intent = new Intent(this, LoginActivity_.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                MainActivity.this.finish();
            }
        };
        timer.schedule(task, 1500);
    }

}
