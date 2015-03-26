package com.olme.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.olme.R;
import com.olme.application.CustomApplication;
import com.olme.application.ExitApplication;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Bingo on 2014/8/13.
 * 退出界面
 */

@EActivity(R.layout.activity_logout_dialog)
public class LogoutActivity extends Activity {

    @ViewById(R.id.logoff)
    LinearLayout logoff;

    private CustomApplication app;

    @Click(R.id.closeApp)
    void closeAppIsClick(){
        ExitApplication.getInstance().exit();
    }

    @Click(R.id.logoff)
    void logoutIsClick(){
        System.out.println("----------->logout<-------");
        app = (CustomApplication) getApplication();
        app.setValue("logout");
        Intent intent = new Intent(this,LoginActivity_.class);
        intent.putExtra("logout", 1);
        startActivity(intent);
    }

    @AfterViews
    void init(){
        ExitApplication.getInstance().addActivity(this);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //设置窗口的大小及透明度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = layoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        this.setFinishOnTouchOutside(true);

        app = (CustomApplication) getApplication();
        if("Login".equals(app.getValue())){
            logoff.setVisibility(View.VISIBLE);
        }else {
            logoff.setVisibility(View.GONE);
        }

    }
}
