package com.olme.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import com.olme.application.ExitApplication;
import com.olme.tool.ResultIntent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Bingo on 2014/8/15.
 * 修改我的地址
 */
@EActivity(R.layout.activity_modifyaddress)
public class ModifyAddressActivity extends Activity {
    @ViewById(R.id.modifyAddress)
    EditText content;

    @ViewById(R.id.addressSave)
    Button save;

    private SharedPreferences.Editor sharedata;
    private String tempData;
    private String oldAddress;

    @Click(R.id.addressSave)
    void saveIsClick() {
        tempData = content.getText().toString();
        if(!tempData.equals(oldAddress)) {
            //更新数据到服务器，成功后再保存到本地；
            sharedata.putString("userAddress", tempData);
            sharedata.commit();
            System.out.println(tempData);
        }
        ResultIntent.resultIntent(this, tempData);
        finish();
    }

    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);
        //获取本地数据
        SharedPreferences  sp = this.getSharedPreferences("userInfo", 0);
        //获取本地数据编辑器
        sharedata = sp.edit();
        oldAddress = sp.getString("userAddress","");
        content.setText(oldAddress);

        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //设置窗口的大小及透明度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = layoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        this.setFinishOnTouchOutside(true);
    }

}
