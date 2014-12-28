package com.olme.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.olme.application.ExitApplication;
import com.olme.tool.ResultIntent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Bingo on 2014/8/15.
 * 修改我的电话
 */
@EActivity(R.layout.activity_modifyphone)
public class ModifyPhoneActivity extends Activity{
    @ViewById(R.id.modifyPhone)
     EditText content;
    /**
     * 修改前的手机号码
     */
    private String oldPhoneNumber;
    /**
     * 本地数据，键值为userInfo
     */
    private SharedPreferences.Editor sharedata;
    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);
        //获取本地数据
        SharedPreferences  sp = this.getSharedPreferences("userInfo", 0);
        sharedata = sp.edit();

        //获取修改前的号码
        this.oldPhoneNumber = sp.getString("userPhone","");
        this.content.setText(this.oldPhoneNumber);

        //初始化界面
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //设置窗口的大小及透明度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = layoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        this.setFinishOnTouchOutside(true);
    }

    /**
     * 保存按钮监听器，对用户手机号进行判断并进行相应处理
     */
    @Click(R.id.phoneSave)
     void savePhone(){
        //获取文本框的用户名
        String tempData = content.getText().toString();
        //若用户手机号改变，将结果保存到本地
        if(!tempData.equals(this.oldPhoneNumber)){
            //TODO更新数据到服务器，成功后再保存到本地
            this.sharedata.putString("userPhone",tempData);
            //提交本地数据
            this.sharedata.commit();
        }
        ResultIntent.resultIntent(this, tempData);
        //关闭当前页面
        this.finish();

    }
}
