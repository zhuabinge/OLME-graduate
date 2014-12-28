package com.olme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.olme.popupWindow.MorePopWindow;
import com.olme.api.UserApi;
import com.olme.application.CustomApplication;
import com.olme.application.ExitApplication;
import com.olme.domain.LoginUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Created by Bingo on 2014/8/1
 * 登陆
 */

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {
    @ViewById(R.id.returnbt)
    ImageView returnbt;

    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.et_username)
    EditText etUsername;

    @ViewById(R.id.et_password)
    EditText etPassword;

    @ViewById(R.id.checkboxpw)
    CheckBox cbpassword;

    @ViewById(R.id.checkboxlg)
    CheckBox cblogin;

    @ViewById(R.id.btn_login)
    Button btn;

    @ViewById(R.id.btn_register)
    Button btr;

    @RestService
    UserApi olmeApi;

    String username;
    String password;
    private SharedPreferences.Editor sharedata;
    private LayoutInflater inflater;
    private View views;
    private CustomApplication app;
    Toast toast;

    @AfterViews
    void init() {
        ExitApplication.getInstance().addActivity(this);
        app = (CustomApplication) getApplication();
        sharedata = getSharedPreferences("userInfo", 0).edit();
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_login, null, false);
        initEditView();
    }


    public void initEditView() {
        SharedPreferences sp = sp = this.getSharedPreferences("userInfo", 0);
        if (sp.getBoolean("ISCHECK", false)) {
            etUsername.setText(sp.getString("userEmail", ""));
            etPassword.setText(sp.getString("password", ""));
        }
    }

//    @CheckedChange(R.id.checkboxpw)
//    void cbpasswordIsChecked() {
//        if (cbpassword.isChecked()) {
//            sharedata.putBoolean("ISCHECK", true);
//            sharedata.commit();
//            app.setValue("Login");
//            //sp.edit().putBoolean("ISCHECK", true).commit();
//        } else {
//            System.out.println("记住密码没有选中");
//            sharedata.putBoolean("ISCHECK", false);
//            sharedata.commit();
//            app.setValue("Logout");
//        }
//    }
//
//    @CheckedChange(R.id.checkboxlg)
//    void cbloginIsChecked() {
//        if (cblogin.isChecked()) {
//            sharedata.putBoolean("AUTO_ISCHECK", true);
//            sharedata.commit();
//        } else {
//            sharedata.putBoolean("AUTO_ISCHECK", false);
//            sharedata.commit();
//        }
//    }

    @Click(R.id.btn_login)
    void btnIsClicked() {
        username = etUsername.getText().toString().trim();  //获取页面填的账号信息
        password = etPassword.getText().toString().trim();  //获取页面填的密码信息
        System.out.println("test" + username + "test");
        System.out.println("test" + password + "test");
        if (!"".equals(username) && !"".equals(password)) {
            //login(username, password);
            app.setValue("Login");
            Intent intent = new Intent(this,InitActivity_.class);
            startActivity(intent);
        } else {
            toast = Toast.makeText(this,
                    "帐号和密码不能为空！", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    @Click(R.id.btn_register)
    void btrIsClicked() {
        Intent intent = new Intent(this, RegisterActivity_.class);
        startActivity(intent); //前往注册页面
    }

    @Click(R.id.returnbt)
    void returnbtIsClick() {
        LoginActivity.this.finish();  //结束本Activity
    }


    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(LoginActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }

    @Background
    void login(String userEmail, String userPw) {
        try {
            LoginUser user = olmeApi.login(userEmail, userPw); //验证登陆
            app.setValue("Login");
            System.out.println(user.toString());
            if (cbpassword.isChecked()) {  //记住用户名、密码、

                sharedata.putBoolean("ISCHECK", true);
                if (cblogin.isChecked()) {  //自动登陆
                    sharedata.putBoolean("AUTO_ISCHECK", true);
                } else {
                    sharedata.putBoolean("AUTO_ISCHECK", false);
                }
            } else {  //取消自动登陆
                sharedata.putBoolean("ISCHECK", false);
            }
            sharedata.putInt("userId",user.getUserId());
            sharedata.putString("userEmail", user.getUserEmail());
            sharedata.putString("password", user.getPassword());
            sharedata.putString("userName",user.getUserName());
            sharedata.putString("userPhoto",user.getUserPhoto());
            sharedata.putString("userPhone",user.getUserPhone());
            sharedata.putString("userAddress",user.getUserAddress());
            sharedata.putString("userSex",user.getUserSex());
            sharedata.commit(); //提交


//            Intent intent = new Intent();
//            startActivity();

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

    private boolean vilidata(String username, String password) {
        if ("".equals(username))
            return false;
        if ("".equals(password))
            return false;
        return true;
    }
}
