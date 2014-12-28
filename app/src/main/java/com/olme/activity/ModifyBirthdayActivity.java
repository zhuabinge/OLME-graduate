package com.olme.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;

import com.olme.application.ExitApplication;
import com.olme.tool.ResultIntent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Bingo on 2014/8/15.
 * 修改我的生日
 */
@EActivity(R.layout.activity_modifybirthday)
public class ModifyBirthdayActivity extends Activity {
    @ViewById(R.id.datePicker)
    DatePicker datePicker;

    @ViewById(R.id.birthdaySave)
    Button birthdaySave;

    private SharedPreferences.Editor sharedata;
    private String tempData;
    private String oldBirthday;
    Intent intent;

    @AfterViews
    void init() {
        int date;
        int year;
        int yue;
        ExitApplication.getInstance().addActivity(this);
        sharedata = getSharedPreferences("userInfo", 0).edit();
        //获取本地数据
        SharedPreferences sp = this.getSharedPreferences("userInfo", 0);
        oldBirthday = sp.getString("userBirthday", "");
//        content.setText(oldAddress);
        if (oldBirthday != "") {
            String[] dateStr = oldBirthday.split("-");
            date = Integer.parseInt(dateStr[2]);
            yue = Integer.parseInt(dateStr[1]) - 1;
            year = Integer.parseInt(dateStr[0]);
            datePicker.updateDate(year, yue, date);
        }
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //设置窗口的大小及透明度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = layoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        this.setFinishOnTouchOutside(true);
    }


    @Click(R.id.birthdaySave)
    void birthdaySaveIsClicked() {
        tempData = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();

        System.out.print(tempData);
        sharedata.putString("userBirthday", tempData);
        sharedata.commit();
        tempData = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();
        ResultIntent.resultIntent(this, tempData);
        finish();//必须手动finish
    }
}
