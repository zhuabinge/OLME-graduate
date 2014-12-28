package com.olme.activity;

import android.app.Activity;
import android.content.Intent;
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
 * 修改我的昵称
 */
@EActivity(R.layout.activity_modifyname)
public class ModifyNameActivity extends Activity{
    /**
     * 显示名字的EditText
     */
    @ViewById(R.id.modifyName)
    EditText content;
    /**
     * 用户修改前的名字
     */
    private String oldName;
    /**
     * 本地数据，键值为userInfo
     */
    private SharedPreferences.Editor sharedata;
    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);
        //获取本地数据
        SharedPreferences  sp = this.getSharedPreferences("userInfo", 0);
        //获取本地数据编辑器
        sharedata = sp.edit();

       // Intent intent = getIntent();
        //获取本地记录中的用户名
        oldName = sp.getString("userName","");
        //设置名字文本框的值
        content.setText(oldName);
        //初始化窗口
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //设置窗口的大小及透明度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = layoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        this.setFinishOnTouchOutside(true);
    }

    /**
     * 保存按钮监听器，对用户名进行判断并进行相应处理
     */
    @Click(R.id.nameSave)
     void saveIsClick(){
        //获取文本框的用户名
        String tempData = content.getText().toString();
        //若用户名改变，将结果保存到本地
        if(!tempData.equals(this.oldName)){
            //TODO更新数据到服务器，成功后再保存到本地
            this.sharedata.putString("userName",tempData);
            //提交本地数据
            this.sharedata.commit();
        }
        ResultIntent.resultIntent(this,tempData);
        //关闭当前页面
        this.finish();
    }
}
