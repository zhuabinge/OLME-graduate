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
import com.olme.api.CommunicationApi;
import com.olme.domain.RestResult;
import com.olme.popupWindow.MorePopWindow;
import com.olme.application.ExitApplication;
import com.olme.tool.MyErrorHandler;
import com.olme.tool.UIHelper;
import com.olme.tool.UrlUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Bingo on 2014/8/12.
 * 添加主题
 */
@EActivity(R.layout.activity_addtheme)
public class AddThemeActivity extends Activity{

    @ViewById(R.id.morebt)
    ImageView morebt;

    @ViewById(R.id.themeName)
    EditText themeName;

    @ViewById(R.id.themeContent)
    EditText themeContent;

    private LayoutInflater inflater;
    private View views;
    private Toast toast;
    private Intent intent;
    private int userId;
    private RestResult addQuestion = null;

    @RestService
    CommunicationApi communicationApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;

    @AfterViews
    void init(){

        //设置ErrorHandler
        communicationApi.setRestErrorHandler(errorHandlerForUserService);
        ExitApplication.getInstance().addActivity(this);
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_addtheme, null, false);

        intent = getIntent();
        userId = intent.getIntExtra("userId", 1);
    }

    @Click(R.id.returnbt)
    void returnbtIsClick() {
        AddThemeActivity.this.finish();  //结束本Activity
    }

    @Click(R.id.morebt)
    void morebtIsClick() {
        MorePopWindow morePopWindow = new MorePopWindow(AddThemeActivity.this, views);
        morePopWindow.showPopupWindow(morebt);  //显示more窗口
    }

    @Click(R.id.replyUser)
    void addTheme() {

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                UIHelper.showDialogForLoading(AddThemeActivity.this, views);
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

                    String questionTheme = themeName.getText().toString();
                    String comContent = themeContent.getText().toString();
                    addQuestion = communicationApi.addCommunication(userId, questionTheme, comContent, UrlUtil.getIsAppLogin()); //获取全部课程
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
                    if (addQuestion.getCode() == 0) {
                        toast = Toast.makeText(AddThemeActivity.this,
                                addQuestion.getMsg(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        setView();
                    }
                } else {
                    // 加载失败
                    toast = Toast.makeText(AddThemeActivity.this,
                            "error", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        }.execute();
    }

    @UiThread
    void setView() {
        toast = Toast.makeText(AddThemeActivity.this,
                addQuestion.getMsg(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        this.setResult(1, intent);
        this.finish();
    }
}
