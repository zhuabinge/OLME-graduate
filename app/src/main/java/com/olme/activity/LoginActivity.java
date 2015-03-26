package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.olme.R;
import com.olme.api.UserApi;
import com.olme.domain.RestResult;
import com.olme.domain.UserResult;
import com.olme.popupWindow.MorePopWindow;
import com.olme.application.CustomApplication;
import com.olme.application.ExitApplication;
import com.olme.tool.JsonUtil;
import com.olme.tool.MyErrorHandler;
import com.olme.tool.UIHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Bingo on 2014/8/1
 * 登陆
 */

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.et_username)
    EditText etUsername;

    @ViewById(R.id.et_password)
    EditText etPassword;

    @ViewById(R.id.checkboxpw)
    CheckBox cb_pw;

    @ViewById(R.id.checkboxlg)
    CheckBox cb_autolg;

    @RestService
    UserApi olmeApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;

    private String userName;
    private String password;
    private SharedPreferences.Editor sharedata;
    private LayoutInflater inflater;
    private View views;
    private CustomApplication app;
    private boolean loginResult = false;
    private Toast toast;
    private RestResult userInfo = null;

    @AfterViews
    void init() {

        //设置ErrorHandler
        olmeApi.setRestErrorHandler(errorHandlerForUserService);
        ExitApplication.getInstance().addActivity(this);
        app = (CustomApplication) getApplication();
        sharedata = getSharedPreferences("olme_userInfo", 0).edit();
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(R.layout.activity_login, null, false);    //获取自定义布局文件dialog.xml的视图
        SharedPreferences sp = this.getSharedPreferences("olme_userInfo", 0);

        if (sp.getBoolean("remem_pw", false)) {
            etUsername.setText(sp.getString("username", ""));
            etPassword.setText(sp.getString("userpw", ""));
            cb_pw.setChecked(true);
        }
        if (sp.getBoolean("auto_log", false)) {
            cb_autolg.setChecked(true);
            Intent inte = getIntent();
            if (inte.getIntExtra("logout", 0) == 0) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        loginActionDeal();
                    }
                }, 100);
            }
        }
    }

    public void loginActionDeal(){
        userName = etUsername.getText().toString().trim();  //获取页面填的账号信息
        password = etPassword.getText().toString().trim();  //获取页面填的密码信息
        if (!"".equals(userName) && !"".equals(password)) {
            System.out.println("loginActionDeal   " + userName + "   " + password);
            login();
//            Intent intent = new Intent(LoginActivity.this, VedioPlayerActivity.class);
//            startActivity(intent);
        } else {
            toast = Toast.makeText(this,
                    "帐号和密码不能为空！", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    @Click(R.id.btn_login)
    void btn_lgIsClicked() {
        loginActionDeal();
    }

    @Click(R.id.btn_register)
    void btn_rgIsClicked() {
        Intent intent = new Intent(this, RegisterActivity_.class);
        startActivity(intent); //前往注册页面
    }

    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(LoginActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }

    public void login() {

        System.out.println("login()");
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //显示加载进度对话框
                UIHelper.showDialogForLoading(LoginActivity.this, views);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                    //在这里添加调用接口获取数据的代码
                    //doSomething()

                    //String userPw = MD5Util.MD5(password);
                    String userPw = password;
                    userInfo = olmeApi.tuserLogin(userName, userPw); //验证登陆

                    if (userInfo == null) {
                        throw new Exception("无网络连接！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccess) {

                //关闭对话框
                UIHelper.hideDialogForLoading();
                if (isSuccess) {
                    // 加载成功
//                    UserResult userResult = null;
                    int code = userInfo.getCode();
                    if (code == 0) {
                        toast = Toast.makeText(LoginActivity.this,
                                userInfo.getMsg(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        loginResult = false;
                    } else if (code == 1) {
                        String json = userInfo.getData().toString();
                        System.out.println(json);
                        UserResult userResult = JsonUtil.fromObject(json, UserResult.class);
                        app.setUserId(userResult.getUserId());
                        app.setUserName(userResult.getUserName());
                        app.setUserSex(userResult.getUserSex());
                        app.setUserPhoto(userResult.getUserPhoto());
                        loginResult = true;
                    }
                } else {
                    // 加载失败
                    toast = Toast.makeText(LoginActivity.this,
                            "无网络连接！", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    loginResult = false;
                }

                if (loginResult) {
                    app.setValue("Login");
                    if (cb_pw.isChecked()) {  //记住用户名、密码、
                        sharedata.putString("username", userName);
                        sharedata.putString("userpw", password);
                        sharedata.putBoolean("remem_pw", true);
                        if (cb_autolg.isChecked()) {  //自动登陆
                            sharedata.putBoolean("auto_log", true);
                        } else {
                            sharedata.putBoolean("auto_log", false);
                        }
                    } else {  //取消自动登陆
                        sharedata.putBoolean("remem_pw", false);
                        sharedata.putBoolean("auto_log", false);
                    }
                    sharedata.commit(); //提交
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(LoginActivity.this, VedioPlayActivity.class);
                    startActivity(intent);
//                    Intent intent = new Intent(LoginActivity.this, InitActivity_.class);
//                    startActivity(intent);
                    LoginActivity.this.finish();
                }
            }
        }.execute();
    }
}
