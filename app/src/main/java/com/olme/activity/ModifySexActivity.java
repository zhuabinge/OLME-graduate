package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.olme.R;
import com.olme.api.UserApi;
import com.olme.application.CustomApplication;
import com.olme.application.ExitApplication;
import com.olme.domain.RestResult;
import com.olme.tool.MyErrorHandler;
import com.olme.tool.UIHelper;
import com.olme.tool.UrlUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Bingo on 2014/8/15.
 * 修改我的性别
 */

@EActivity(R.layout.activity_modifysex)
public class ModifySexActivity extends Activity{

    private Toast toast;
    private Intent intent;
    private int userId;
    private RestResult modifySex = null;
    private View views;
    private LayoutInflater inflater;
    private CustomApplication app;
    private String oldSex;
    private String newSex;

    @ViewById(R.id.selectBoy)
    ImageView imageViewMan;

    @ViewById(R.id.selectGirl)
    ImageView imageViewWoman;

    @RestService
    UserApi userApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;

    @AfterViews
    void init() {
        //设置ErrorHandler
        userApi.setRestErrorHandler(errorHandlerForUserService);
        ExitApplication.getInstance().addActivity(this);
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_modifyname, null, false);

        app = (CustomApplication) getApplication();
        userId = app.getUserId();
        intent = getIntent();

        //获取修改前性别
        this.oldSex = app.getUserSex();
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
            sendUserSex("男");
        } else {
            ModifySexActivity.this.setResult(1, intent);
            //关闭当前页面
            ModifySexActivity.this.finish();
        }
    }

    @Click(R.id.linear_woman)
     void womanOnClick(){
        //修改前性别为男性
        if(!"女".equals(this.oldSex)){
            //TODO将数据保存到数据库中
            //修改本地记录
            sendUserSex("女");
        } else {
            ModifySexActivity.this.setResult(1, intent);
            //关闭当前页面
            ModifySexActivity.this.finish();
        }
    }


    public void sendUserSex(final String sex) {
        //TODO更新数据到服务器，成功后再保存到本地
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                UIHelper.showDialogForLoading(ModifySexActivity.this, views);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                    //在这里添加调用接口获取数据的代码
                    //doSomething()

                    /* param[0] 代表问题的id, 0 代表首次获取提问列表
                    ** param[2] 0 代表上拉加载, 1 代表下拉更新
                    */

                    modifySex = userApi.updateTUser(userId, sex, UrlUtil.getIsAppLogin()); //获取全部课程
//                    if (course == null) {
//                        throw new Exception();
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccess) {
                System.out.println("execute()");
                UIHelper.hideDialogForLoading();
                if (isSuccess) {
                    // 加载成功
                    //
                    if (modifySex.getCode() == 0) {
                        toast = Toast.makeText(ModifySexActivity.this,
                                modifySex.getMsg(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        app.setUserSex(sex);
                    }
                } else {
                    // 加载失败
                    toast = Toast.makeText(ModifySexActivity.this,
                            "error", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                ModifySexActivity.this.setResult(1, intent);
                //关闭当前页面
                ModifySexActivity.this.finish();
            }
        }.execute();
    }
}
