package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.olme.R;
import com.olme.domain.RestResult;
import com.olme.popupWindow.MorePopWindow;
import com.olme.api.UserApi;
import com.olme.application.ExitApplication;
import com.olme.tool.MD5Util;
import com.olme.tool.MyErrorHandler;
import com.olme.tool.UIHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Bingo on 2014/8/7.
 * 注册界面
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends Activity {
    @ViewById(R.id.rg_username)
    EditText etUsername;

    @ViewById(R.id.rg_password)
    EditText etPassword;

    @ViewById(R.id.rg_surepassword)
    EditText etsPassword;

    @ViewById(R.id.morebt)
    ImageView morebt;

    @RestService
    UserApi olmeApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;

    private LayoutInflater inflater;
    private View views;
    private String username;
    private String password;
    private String surepassword;
    private Toast toast;
    private RestResult userInfo = null;
    private boolean registerResult = false;

    @AfterViews
    void init() {

        //设置ErrorHandler
        olmeApi.setRestErrorHandler(errorHandlerForUserService);
        ExitApplication.getInstance().addActivity(this);
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_register, null, false);
    }

    @Click(R.id.btn_register)
    void regbtIsClick() {
        username = etUsername.getText().toString().trim();  //获取页面填的账号信息
        password = etPassword.getText().toString().trim();  //获取页面填的密码信息
        surepassword = etsPassword.getText().toString().trim();  //获取页面填的密码信息
        if (!"".equals(username) && !"".equals(password) && !"".equals(surepassword)) {
            if (password.equals(surepassword)) {
               register();
            } else {
                toast = Toast.makeText(RegisterActivity.this,
                        "两次输入的密码不一致，请重新输入！", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        } else {
            toast = Toast.makeText(RegisterActivity.this,
                    "不能为空，请重新输入！", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void register() {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //显示加载进度对话框
                UIHelper.showDialogForLoading(RegisterActivity.this, views);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                    //在这里添加调用接口获取数据的代码
                    //doSomething()

                    //String userPw = MD5Util.MD5(password);
                    String userPw = password;
                    userInfo = olmeApi.tuserRegister(username, userPw); //验证登陆
                    System.out.println("-------->" + userInfo.getCode());
                    if (userInfo == null) {
                        throw new Exception("无网络连接！");
                    }
                } catch (Exception e) {
                    System.out.println("----->e.toString() " + e.toString());
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccess) {
                //关闭对话框
                UIHelper.hideDialogForLoading();
                if (isSuccess) {
                    // 加载成功;
                    int code = userInfo.getCode();
                    if (code == 0) {
                        toast = Toast.makeText(RegisterActivity.this,
                                userInfo.getMsg(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        registerResult = false;
                    } else if (code == 1) {
                        tip();
                        registerResult = true;
                    }
                } else {
                    // 加载失败
                    toast = Toast.makeText(RegisterActivity.this,
                            "无网络连接！", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    registerResult = false;
                }
                if (registerResult) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity_.class);
                    startActivity(intent);
                    RegisterActivity.this.finish();
                }
            }
        }.execute();
    }

    @UiThread
    void tip() {
        toast = Toast.makeText(RegisterActivity.this,
                "注册成功！", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    @Click(R.id.returnbt)
    void returnbtIsClick() {
        RegisterActivity.this.finish();  //结束本Activity
    }

    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(RegisterActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }
}
