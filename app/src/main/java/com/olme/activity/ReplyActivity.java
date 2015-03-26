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
import com.olme.api.CommunicationApi;
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
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Bingo on 2014/8/17.
 */

@EActivity(R.layout.activity_reply)
public class ReplyActivity extends Activity {

    @ViewById(R.id.beReplyContent)
    EditText editText;

    @RestService
    CommunicationApi communicationApi;

    @Bean
    MyErrorHandler errorHandlerForUserService;

    private Intent intent;
    private int questionId;
    private RestResult addAnswer = null;
    private LayoutInflater inflater;
    private View views;
    private Toast toast;
    private int userId;
    private CustomApplication app;


    @AfterViews
    void init() {

        //设置ErrorHandler
        communicationApi.setRestErrorHandler(errorHandlerForUserService);
        ExitApplication.getInstance().addActivity(this);
        //初始化界面
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //设置窗口的大小及透明度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = layoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
        this.setFinishOnTouchOutside(true);
        app = (CustomApplication) getApplication();
        userId = app.getUserId();

        intent = getIntent();
        questionId = intent.getIntExtra("questionId", 1);

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        views = inflater.inflate(    //获取自定义布局文件dialog.xml的视图
                R.layout.activity_reply, null, false);
    }

    @Click(R.id.submitReply)
    void onSubmit(){
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                UIHelper.showDialogForLoading(ReplyActivity.this, views);
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

                    String comContent = editText.getText().toString();
                    addAnswer = communicationApi.addComment(userId,questionId, comContent, UrlUtil.getIsAppLogin()); //获取全部课程
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
                    if (addAnswer.getCode() == 0) {
                        toast = Toast.makeText(ReplyActivity.this,
                                addAnswer.getMsg(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        setView();
                    }
                } else {
                    // 加载失败
                    toast = Toast.makeText(ReplyActivity.this,
                            "error", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        }.execute();
    }

    @UiThread
    void setView() {
        toast = Toast.makeText(ReplyActivity.this,
                addAnswer.getMsg(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        this.setResult(1, intent);
        this.finish();
    }
}
