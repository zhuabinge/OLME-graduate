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
import android.widget.EditText;
import android.widget.Toast;

import com.olme.R;
import com.olme.api.UserApi;
import com.olme.application.CustomApplication;
import com.olme.application.ExitApplication;
import com.olme.domain.RestResult;
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

    @RestService
    UserApi userApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;

    /**
     * 用户修改前的名字
     */
    private String oldName;
    private String newName;
    private Toast toast;
    private Intent intent;
    private int userId;
    private RestResult modifyName = null;
    private View views;
    private LayoutInflater inflater;
    private CustomApplication app;

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

       // Intent intent = getIntent();
        //获取本地记录中的用户名
        oldName = app.getUserName();
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
        newName = content.getText().toString();
        //若用户名改变，将结果保存到本地
        if(!newName.equals(this.oldName)){
            //TODO更新数据到服务器，成功后再保存到本地
            new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    UIHelper.showDialogForLoading(ModifyNameActivity.this, views);
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

                        //modifyName = userApi.modifyName(userId, newName, UrlUtil.getIsAppLogin()); //获取全部课程
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
                        if (modifyName.getCode() == 0) {
                            toast = Toast.makeText(ModifyNameActivity.this,
                                    modifyName.getMsg(), Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        } else {
                            setView();
                        }
                    } else {
                        // 加载失败
                        toast = Toast.makeText(ModifyNameActivity.this,
                                "error", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }.execute();

        }
    }

    @UiThread
    void setView() {
        app.setUserName(newName);
        toast = Toast.makeText(ModifyNameActivity.this,
                modifyName.getMsg(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        this.setResult(1, intent);
        //关闭当前页面
        this.finish();
    }
}
