package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.olme.popupWindow.MorePopWindow;
import com.olme.api.UserApi;
import com.olme.application.ExitApplication;
import com.olme.domain.LoginUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.HttpStatusCodeException;

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

    private SharedPreferences.Editor sharedata;
    private LayoutInflater inflater;
    private View views;
    private String username;
    private String password;
    private String surepassword;
    private Toast toast;
    private String flag;

    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);
        sharedata = getSharedPreferences("userInfo", 0).edit();
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
               // register(username, password);


//                while(this.flag == null){
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

//                if (flag.equals("true")) {
                    toast = Toast.makeText(RegisterActivity.this,
                            "注册成功！", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                Intent intent = new Intent(RegisterActivity.this, LoginActivity_.class);
                startActivity(intent);
//                } else {
//                    toast = Toast.makeText(RegisterActivity.this, "该邮箱已被注册，请选择其他邮箱！", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                    toast.show();
//                }
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


    @Background
    void register(String userEmail, String userPw) {
        try {
            Boolean user = olmeApi.regist(userEmail, userPw);
            System.out.println(user);
            flag = user.toString();

        } catch (HttpStatusCodeException e) {
            showErroResult(e.getStatusCode().value());
        }
    }

    /**
     * 展示http请求异常结果
     *
     * @param requestCode
     */
    @UiThread
    void showErroResult(int requestCode) {
        if (requestCode == 404) {

        } else {
        }
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
