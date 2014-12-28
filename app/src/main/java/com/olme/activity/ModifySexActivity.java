package com.olme.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.olme.application.ExitApplication;
import com.olme.tool.ResultIntent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Bingo on 2014/8/15.
 * 修改我的性别
 */

@EActivity(R.layout.activity_modifysex)
public class ModifySexActivity extends Activity{

    private String oldSex;
    /**
     * 本地数据编辑器
     */
    private SharedPreferences.Editor sharedata;
    @ViewById(R.id.selectBoy)
     ImageView imageViewMan;
    @ViewById(R.id.selectGirl)
     ImageView imageViewWoman;
    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);
        //获取本地数据
        SharedPreferences  sp = this.getSharedPreferences("userInfo", 0);
        //获取本地数据编辑器
        sharedata = sp.edit();

        //获取修改前性别
        this.oldSex = sp.getString("userSex","");
        if(this.oldSex.equals("男")){
            this.imageViewMan.setImageResource(R.drawable.defalt_head);
        }
        else{
            this.imageViewWoman.setImageResource(R.drawable.defalt_head);
        }
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
     * 性别TextView的监听器，将点击的TextView保存到本地
     */

    @Click(R.id.linear_man)
     void textViewOnclick(){

                //修改前性别为女性
                if(!"男".equals(this.oldSex)){
                    //TODO将数据保存到数据库中
                    //修改本地记录
                    this.sharedata.putString("userSex","男");
                    //提交到本地记录
                    this.sharedata.commit();

                }
        ResultIntent.resultIntent(this,"男");
        this.finish();
    }
    @Click(R.id.linear_woman)
     void womanOnClick(){
        //修改前性别为男性
        if(!"女".equals(this.oldSex)){
            //TODO将数据保存到数据库中
            //修改本地记录
            this.sharedata.putString("userSex","女");
            this.sharedata.commit();
        }
        ResultIntent.resultIntent(this,"女");
        this.finish();
    }


}
